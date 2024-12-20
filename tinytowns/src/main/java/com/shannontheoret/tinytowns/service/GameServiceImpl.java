package com.shannontheoret.tinytowns.service;

import com.shannontheoret.tinytowns.*;
import com.shannontheoret.tinytowns.dao.GameDao;
import com.shannontheoret.tinytowns.entity.JPAGame;
import com.shannontheoret.tinytowns.entity.JPAPlayer;
import com.shannontheoret.tinytowns.exceptions.GameCodeNotFoundException;
import com.shannontheoret.tinytowns.exceptions.InternalGameException;
import com.shannontheoret.tinytowns.exceptions.InvalidMoveException;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private GameDao gameDao;
    private BuildingMap buildingMap;

    @Autowired
    public GameServiceImpl(GameDao gameDao, BuildingMap buildingMap) {
        this.gameDao = gameDao;
        this.buildingMap = buildingMap;
    }

    private static final Random RANDOM = new Random();

    @Override
    @Transactional
    public List<JPAGame> findAll() {
        return gameDao.findAll();
    }

    @Override
    @Transactional
    public JPAGame findByCode(String code)  throws GameCodeNotFoundException {
        JPAGame game = gameDao.findByCode(code);
        if (game==null) {
            throw new GameCodeNotFoundException(code);
        }
        return game;
    }

    @Override
    @Transactional
    public void save(JPAGame game) {
        gameDao.save(game);
    }

    @Override
    @Transactional
    public JPAGame newGame() {
        JPAGame game = new JPAGame();
        game.setCode(generateCode());
        game.setStep(GameStep.SETUP);
        save(game);
        return game;
    }

    @Override
    @Transactional
    public JPAGame addPlayer(String gameCode, String playerName) throws InvalidMoveException, GameCodeNotFoundException {
        JPAGame game = findByCode(gameCode);
        if (game.getStep() != GameStep.SETUP) {
            throw new InvalidMoveException("Game is not in the setup phase.");
        }
        if (Rules.MAX_PLAYERS > game.getPlayers().size()) {
            JPAPlayer player = new JPAPlayer();
            player.setName(playerName);
            player.setGame(game);
            player.setPlayerOrder(game.getPlayers().size());
            game.getPlayers().add(player);
            save(game);
        } else {
            throw new InvalidMoveException("Maximum player count of " + Rules.MAX_PLAYERS + " has already been reached.");
        }
        return game;
    }

    @Override
    @Transactional
    public JPAGame startGame(String gameCode) throws InvalidMoveException, GameCodeNotFoundException {
        JPAGame game = findByCode(gameCode);
        if (game.getStep() != GameStep.SETUP) {
            throw new InvalidMoveException("Game is not in the setup phase.");
        }
        if (Rules.MIN_PLAYERS <= game.getPlayers().size()) {
            JPAPlayer first = game.getPlayers().get(0);
            first.setMasterBuilder(true);
            game.setStep(GameStep.TO_NAME);
            game.setCards(generateCardMap());
            save(game);
        } else {
            throw new InvalidMoveException("Require " + (Rules.MIN_PLAYERS - game.getPlayers().size()) + " more players to start game.");
        }
        return game;
    }

    @Override
    @Transactional
    public JPAGame namePiece(String gameCode, Piece piece) throws InvalidMoveException, GameCodeNotFoundException {
        JPAGame game = findByCode(gameCode);
        if (game.getStep() != GameStep.TO_NAME) {
            throw new InvalidMoveException("Cannot name piece. Incorrect step of game play.");
        }
        if (!Piece.getColouredBlocks().contains(piece)) {
            throw new InvalidMoveException("Not a valid resource.");
        }
        game.setResource(piece);
        game.setStep(GameStep.TO_PLACE);
        for (JPAPlayer player : game.getPlayers()) {
            if (!player.isCompletedGrid()) {
                player.setTurnToPlace(true);
            }
        }
        save(game);
        return game;
    }

    @Override
    @Transactional
    public JPAGame placePiece(String gameCode, Long playerId, Integer gridIndex) throws InvalidMoveException,
            GameCodeNotFoundException, InternalGameException {
        if (!RelativePosition.isValidIndex(gridIndex)) {
            throw new InvalidMoveException("Grid index must be between 0 and 15. " + gridIndex + " provided.");
        }
        JPAGame game = findByCode(gameCode);
        if (game.getStep() != GameStep.TO_PLACE) {
            throw new InvalidMoveException(("Cannot place piece. Incorrect step of game play."));
        }
        JPAPlayer player = getPlayerById(game, playerId);

        if (!player.getTurnToPlace()) {
            throw new InvalidMoveException("Player has already placed piece.");
        }
        if (player.getSquares().containsKey(gridIndex)) {
            throw new InvalidMoveException("Piece already occupies square.");
        }
        player.getSquares().put(gridIndex, game.getResource());
        player.setTurnToPlace(false);
        if (!game.getPlayers().stream().anyMatch(gamePlayer -> gamePlayer.getTurnToPlace())) {
            game.setStep(GameStep.TO_BUILD);
            game.getPlayers().forEach(gamePlayer -> gamePlayer.setTurnToBuild(!gamePlayer.isCompletedGrid()));
        }
        save(game);
        return game;
    }

    @Override
    @Transactional
    public JPAGame build(String gameCode, Long playerId, Integer gridIndex, Set<Integer> indexes, BuildingName buildingName) throws InvalidMoveException, GameCodeNotFoundException {
        JPAGame game = findByCode(gameCode);
        if (game.getStep() != GameStep.TO_BUILD) {
            throw new InvalidMoveException("Cannot place piece. Incorrect step of game play.");
        }
        if (game.getCards().get(buildingName.getColor()) != buildingName) {
            throw new InvalidMoveException("Cannot build " + buildingName + " as it is not one of the starting cards.");
        }
        JPAPlayer player = getPlayerById(game, playerId);
        Map<Integer, Piece> portionOfGrid = player.getPortionOfGrid(indexes);
        Building building = buildingMap.getBuildingMap().get(buildingName);
        if (!indexes.contains(gridIndex) && (!building.isAbleToBeConstructedAnywhere() || player.getSquares().containsKey(gridIndex))) {
            throw new InvalidMoveException("Cannot build " + buildingName + " as the square is already occupied");
        }
        if (building.isValidBuild(indexes, portionOfGrid)) {
            for (Integer index : indexes) {
                player.getSquares().remove(index);
            }
            player.getSquares().put(gridIndex, buildingName.getColor());
        } else {
            throw new InvalidMoveException("Cannot build " + buildingName + " at grid index of " + gridIndex);
        }
        save(game);
        return game;
    }

    @Override
    @Transactional
    public JPAGame endTurn(String gameCode) throws GameCodeNotFoundException, InvalidMoveException, InternalGameException {
        JPAGame game = findByCode(gameCode);
        for (JPAPlayer player: game.getPlayers()) {
            player.setTurnToBuild(false);
        }
        game.getPlayers().stream().forEach(player -> {
            if (player.getSquares().size() == 16) {
                player.setCompletedGrid(true);
            }
        });

        if (game.getPlayers().stream().allMatch(gamePlayer -> gamePlayer.isCompletedGrid())) {
            game.setStep(GameStep.END_GAME);
            calculateScores(game);
        } else {
            JPAPlayer currentMasterBuilder = game.getPlayers().stream().filter(gamePlayer -> gamePlayer.getMasterBuilder())
                    .findAny()
                    .orElse(null);
            if (currentMasterBuilder == null) {
                throw new InternalGameException("No master builder set.");
            }
            do {
                currentMasterBuilder.setMasterBuilder(false);
                if (currentMasterBuilder.getPlayerOrder() == (game.getPlayers().size() - 1)) {
                    currentMasterBuilder = game.getPlayers().get(0);
                } else {
                    currentMasterBuilder = game.getPlayers().get(currentMasterBuilder.getPlayerOrder() + 1);
                }
            } while (currentMasterBuilder.isCompletedGrid());
            currentMasterBuilder.setMasterBuilder(true);
            game.setStep(GameStep.TO_NAME);
        }
        save(game);
        return game;
    }

    private static String generateCode() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    private static JPAPlayer getPlayerById(JPAGame game, Long playerId) throws InvalidMoveException {
        JPAPlayer player = game.getPlayers().stream()
                .filter(gamePlayer -> gamePlayer.getId() == playerId)
                .findAny()
                .orElse(null);
        if (player == null) {
            throw new InvalidMoveException("Player with id " + playerId + " does not exist.");
        }
        return player;
    }

    private static Map<Piece, BuildingName> generateCardMap() {
        Map<Piece, BuildingName> cardMap = new EnumMap<Piece, BuildingName>(Piece.class);

        cardMap.put(Piece.BLUE_BUILDING, BuildingName.COTTAGE);
        //disabled for now because difficult to implement
        //cardMap.put(Piece.BLACK_BUILDING, selectRandomBuildingName(BuildingName.getBuildingsByColor(Piece.BLACK_BUILDING)));
        cardMap.put(Piece.RED_BUILDING, selectRandomBuildingName(BuildingName.getBuildingsByColor(Piece.RED_BUILDING)));
        cardMap.put(Piece.GREY_BUILDING, selectRandomBuildingName(BuildingName.getBuildingsByColor(Piece.GREY_BUILDING)));
        cardMap.put(Piece.GREEN_BUILDING, selectRandomBuildingName(BuildingName.getBuildingsByColor(Piece.GREEN_BUILDING)));
        cardMap.put(Piece.YELLOW_BUILDING, selectRandomBuildingName(BuildingName.getBuildingsByColor(Piece.YELLOW_BUILDING)));
        cardMap.put(Piece.ORANGE_BUILDING, selectRandomBuildingName(BuildingName.getBuildingsByColor(Piece.ORANGE_BUILDING)));

        return cardMap;
    }

    private static BuildingName selectRandomBuildingName(Set<BuildingName> buildingNameSet) {
        BuildingName[] buildings = buildingNameSet.toArray(new BuildingName[0]);
        int randomIndex = RANDOM.nextInt(buildings.length);
        return buildings[randomIndex];
    }

    private static void calculateScores(JPAGame game) {
        for (JPAPlayer player: game.getPlayers()) {
            ScoreCalculator scoreCalculator = new ScoreCalculator(game.getCards(), player.getSquares());
            if (game.getCards().get(Piece.GREEN_BUILDING) == BuildingName.FEAST_HALL) {
                JPAPlayer playerToRight;
                if (player.getPlayerOrder() == (game.getPlayers().size() -1)) {
                    playerToRight = game.getPlayers().get(0);
                } else {
                    playerToRight = game.getPlayers().get(player.getPlayerOrder() + 1);
                }
                Integer numberOfFeastHalls = playerToRight.getSquares().entrySet().stream()
                        .filter(entry -> entry.getValue() == Piece.GREEN_BUILDING)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toSet())
                        .size();
                scoreCalculator.setPlayerToRightNumberOfFeastHalls(numberOfFeastHalls);
            }
            player.setScore(scoreCalculator.calculateScore());
            player.setScorePenalty(scoreCalculator.calculatePenalty());
        }
    }

}
