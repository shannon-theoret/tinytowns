package com.shannontheoret.tinytowns;

import com.shannontheoret.tinytowns.dao.GameDao;
import com.shannontheoret.tinytowns.entity.JPAGame;
import com.shannontheoret.tinytowns.entity.JPAPlayer;
import com.shannontheoret.tinytowns.exceptions.GameCodeNotFoundException;
import com.shannontheoret.tinytowns.exceptions.InvalidMoveException;
import com.shannontheoret.tinytowns.service.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    GameDao gameDao;

    BuildingMap buildingMap;

    @InjectMocks
    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        buildingMap = new BuildingMap();
        buildingMap.init();
        MockitoAnnotations.openMocks(this);
        gameService = new GameServiceImpl(gameDao, buildingMap);
    }

    @Test
    public void newGameTest() {
        doNothing().when(gameDao).save(any(JPAGame.class));

        JPAGame game = gameService.newGame();

        assertNotNull(game);
        assertNotNull(game.getCode());
        assertEquals(GameStep.SETUP, game.getStep());
        verify(gameDao, times(1)).save(game);
    }

    @Test
    public void addPlayerTest_firstPlayer() {
        JPAGame game = new JPAGame();
        game.setStep(GameStep.SETUP);
        game.setPlayers(new ArrayList<>());

        doNothing().when(gameDao).save(any(JPAGame.class));
        when(gameDao.findByCode("123")).thenReturn(game);

        assertDoesNotThrow(() -> gameService.addPlayer("123", "Emily"));
        verify(gameDao, times(1)).save(game);
        assertEquals(GameStep.SETUP, game.getStep());
        assertEquals(1, game.getPlayers().size());
        assertEquals(0, game.getPlayers().stream().iterator().next().getPlayerOrder());
        assertEquals("Emily", game.getPlayers().stream().iterator().next().getName());
    }

    @Test
    public void addPlayerTest_wrongStep_throwsInvalidMoveException() {
        JPAGame game = new JPAGame();
        game.setStep(GameStep.TO_BUILD);
        game.setPlayers(new ArrayList<>());

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.addPlayer("123", "Emily"));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void addPlayerTest_sixPlayers_throwsInvalidMoveException() {
        JPAGame game = new JPAGame();
        game.setCode("123");
        game.setStep(GameStep.TO_BUILD);
        List<JPAPlayer> players = new ArrayList<>();
        players.add(new JPAPlayer());
        players.add(new JPAPlayer());
        players.add(new JPAPlayer());
        players.add(new JPAPlayer());
        players.add(new JPAPlayer());
        players.add(new JPAPlayer());
        game.setPlayers(players);

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.addPlayer("123", "Emily"));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void startGame_valid() {
        JPAGame game = new JPAGame();
        game.setCode("123");
        game.setStep((GameStep.SETUP));
        List<JPAPlayer> players = new ArrayList<>();
        players.add(new JPAPlayer());
        players.add(new JPAPlayer());
        game.setPlayers(players);

        doNothing().when(gameDao).save(any(JPAGame.class));
        when(gameDao.findByCode("123")).thenReturn(game);

        assertDoesNotThrow(() -> gameService.startGame("123"));
        assertEquals(GameStep.TO_NAME, game.getStep());
        assertTrue(game.getPlayers().get(0).getMasterBuilder());
        assertFalse(game.getPlayers().get(1).getMasterBuilder());
        Map<Piece, BuildingName> cards = game.getCards();
        assertTrue(cards.containsKey(Piece.GREEN_BUILDING));
        assertEquals(Piece.GREEN_BUILDING, cards.get(Piece.GREEN_BUILDING).getColor());
        assertTrue(cards.containsKey(Piece.RED_BUILDING));
        assertEquals(Piece.RED_BUILDING, cards.get(Piece.RED_BUILDING).getColor());
        assertTrue(cards.containsKey(Piece.BLUE_BUILDING));
        assertEquals(Piece.BLUE_BUILDING, cards.get(Piece.BLUE_BUILDING).getColor());
        assertTrue(cards.containsKey(Piece.GREY_BUILDING));
        assertEquals(Piece.GREY_BUILDING, cards.get(Piece.GREY_BUILDING).getColor());
        assertTrue(cards.containsKey(Piece.YELLOW_BUILDING));
        assertEquals(Piece.YELLOW_BUILDING, cards.get(Piece.YELLOW_BUILDING).getColor());
        assertTrue(cards.containsKey(Piece.ORANGE_BUILDING));
        assertEquals(Piece.ORANGE_BUILDING, cards.get(Piece.ORANGE_BUILDING).getColor());
        assertEquals(6, cards.size());
        verify(gameDao, times(1)).save(game);
    }

    @Test
    public void startGame_invalidGameStep_throwsInvalidMoveException() {
        JPAGame game = new JPAGame();
        game.setCode("123");
        game.setStep((GameStep.TO_NAME));
        List<JPAPlayer> players = new ArrayList<>();
        players.add(new JPAPlayer());
        players.add(new JPAPlayer());
        game.setPlayers(players);

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.startGame("123"));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void startGame_notEnoughPlayers_throwsInvalidMoveException() {
        JPAGame game = new JPAGame();
        game.setCode("123");
        game.setStep(GameStep.SETUP);
        game.setPlayers(new ArrayList<>());

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.startGame("123"));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void namePiece_valid() {
        JPAGame game = createGameWithTwoPlayers();
        JPAPlayer player = game.getPlayers().get(1);
        player.setPlayerStep(PlayerStep.GRID_COMPLETE);

        doNothing().when(gameDao).save(any(JPAGame.class));
        when(gameDao.findByCode("123")).thenReturn(game);

        assertDoesNotThrow(() -> gameService.namePiece("123", Piece.WOOD));
        assertEquals(Piece.WOOD, game.getResource());
        assertEquals(GameStep.TO_PLACE, game.getStep());
        assertEquals(PlayerStep.PLACE, game.getPlayers().get(0).getPlayerStep());
        assertEquals(PlayerStep.GRID_COMPLETE, game.getPlayers().get(1).getPlayerStep());
        verify(gameDao, times(1)).save(game);
    }

    @Test
    public void namePiece_wrongGameStep_throwsInvalidMoveException() {
        JPAGame game = createGameWithTwoPlayers();
        game.setStep(GameStep.TO_PLACE);

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.namePiece("123", Piece.GLASS));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void namePiece_invalidPiece_throwsInvalidMoveException() {
        JPAGame game = createGameWithTwoPlayers();

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.namePiece("123", Piece.GREEN_BUILDING));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void placePiece_valid() {
        JPAGame game = createGameWithTwoPlayers();
        game.setStep(GameStep.TO_PLACE);
        game.setResource(Piece.BRICK);
        game.getPlayers().get(0).setPlayerStep(PlayerStep.PLACE);
        game.getPlayers().get(1).setPlayerStep(PlayerStep.PLACE);

        doNothing().when(gameDao).save(any(JPAGame.class));
        when(gameDao.findByCode("123")).thenReturn(game);

        assertDoesNotThrow(() -> gameService.placePiece("123", Long.valueOf(1), 12));
        assertEquals(Piece.BRICK, game.getPlayers().get(0).getSquares().get(12));
        assertFalse(game.getPlayers().get(0).getTurnToPlace());
        assertEquals(GameStep.TO_PLACE, game.getStep());
        verify(gameDao, times(1)).save(game);
    }

    @Test
    public void placePiece_lastPlayer_valid() {
        JPAGame game = createGameWithTwoPlayers();
        game.setStep(GameStep.TO_PLACE);
        game.setResource(Piece.BRICK);
        game.getPlayers().get(0).setPlayerStep(PlayerStep.PLACE);

        doNothing().when(gameDao).save(any(JPAGame.class));
        when(gameDao.findByCode("123")).thenReturn(game);

        assertDoesNotThrow(() -> gameService.placePiece("123", Long.valueOf(1), 12));
        assertEquals(GameStep.TO_BUILD, game.getStep());
        verify(gameDao, times(1)).save(game);
    }

    @Test
    public void placePiece_incorrectStep_throwsInvalidMoveException() {
        JPAGame game = createGameWithTwoPlayers();
        game.setResource(Piece.GLASS);
        game.getPlayers().get(0).setPlayerStep(PlayerStep.PLACE);

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.placePiece("123", Long.valueOf(1), 4));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void placePiece_notTurnToPlace_throwsInvalidMoveException() {
        JPAGame game = createGameWithTwoPlayers();
        game.setResource(Piece.WOOD);

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.placePiece("123", Long.valueOf(1), 4));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void placePiece_indexOutOfBounds_throwsInvalidMoveException() {
        JPAGame game = createGameWithTwoPlayers();
        game.setStep(GameStep.TO_PLACE);
        game.setResource(Piece.BRICK);
        game.getPlayers().get(0).setPlayerStep(PlayerStep.PLACE);

        assertThrows(InvalidMoveException.class, () -> gameService.placePiece("123", Long.valueOf(1), 18));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void placePiece_noPlayerId_throwsInvalidMoveException() {
        JPAGame game = createGameWithTwoPlayers();
        game.setStep(GameStep.TO_PLACE);
        game.setResource(Piece.BRICK);
        game.getPlayers().get(0).setPlayerStep(PlayerStep.PLACE);

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.placePiece("123", Long.valueOf(4 ), 4));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void build_valid() throws GameCodeNotFoundException, InvalidMoveException {
        JPAGame game = createGameWithTwoPlayers();
        game.setStep(GameStep.TO_BUILD);
        JPAPlayer player1 = game.getPlayers().get(0);
        player1.setPlayerStep(PlayerStep.BUILD);
        player1.getSquares().put(0, Piece.WOOD);
        player1.getSquares().put(1, Piece.GREY_BUILDING);
        player1.getSquares().put(5, Piece.BLUE_BUILDING);
        player1.getSquares().put(9, Piece.STONE);
        player1.getSquares().put(10, Piece.GLASS);
        player1.getSquares().put(11, Piece.STONE);
        player1.getSquares().put(14, Piece.WOOD);

        when(gameDao.findByCode("123")).thenReturn(game);
        doNothing().when(gameDao).save(any(JPAGame.class));

        gameService.build("123", Long.valueOf(1), 11, Set.of(9,10,11,14), BuildingName.MARKET);

        assertFalse(player1.getSquares().containsKey(9));
        assertFalse(player1.getSquares().containsKey(10));
        assertFalse(player1.getSquares().containsKey(14));
        assertEquals(Piece.YELLOW_BUILDING, player1.getSquares().get(11));
        verify(gameDao, times(1)).save(game);
    }

    @Test
    public void build_incorrectStep() {
        JPAGame game = createGameWithTwoPlayers();
        game.setStep(GameStep.TO_NAME);
        JPAPlayer player1 = game.getPlayers().get(0);
        player1.setPlayerStep(PlayerStep.BUILD);
        player1.getSquares().put(0, Piece.WOOD);
        player1.getSquares().put(1, Piece.GREY_BUILDING);
        player1.getSquares().put(5, Piece.BLUE_BUILDING);
        player1.getSquares().put(9, Piece.STONE);
        player1.getSquares().put(10, Piece.GLASS);
        player1.getSquares().put(11, Piece.STONE);
        player1.getSquares().put(14, Piece.WOOD);

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.build("123", Long.valueOf(1), 11, Set.of(9,10,11,14), BuildingName.MARKET));
        verify(gameDao, times(0)).save(game);
    }

    @Test
    public void build_buildingNotACard_throwsInvalidMoveException() {
        JPAGame game = createGameWithTwoPlayers();
        game.setStep(GameStep.TO_BUILD);
        JPAPlayer player1 = game.getPlayers().get(0);
        player1.setPlayerStep(PlayerStep.BUILD);
        player1.getSquares().put(0, Piece.STONE);
        player1.getSquares().put(1, Piece.GLASS);
        player1.getSquares().put(2, Piece.STONE);
        player1.getSquares().put(5, Piece.WHEAT);
        player1.getSquares().put(8, Piece.GREY_BUILDING);

        Map<BuildingName, Building> map = createBuildingMap();

        when(gameDao.findByCode("123")).thenReturn(game);

        assertThrows(InvalidMoveException.class, () -> gameService.build("123", Long.valueOf(1), 0, Set.of(0,1,2,5), BuildingName.TAILOR));
        verify(gameDao, times(0)).save(game);
    }

    //TODO: test shed
    //TODO: test index not in set

    //TODO: test endTurn()

    private static JPAGame createGameWithTwoPlayers() {
        JPAGame game = new JPAGame();
        game.setCode("123");
        game.setStep(GameStep.TO_NAME);

        JPAPlayer player1 = new JPAPlayer();
        player1.setId(Long.valueOf(1));
        player1.setName("Player1");
        player1.setGame(game);
        player1.setSquares(new TreeMap<>());
        player1.setPlayerOrder(0);
        player1.setMasterBuilder(true);
        player1.setPlayerStep(PlayerStep.SELECT);

        JPAPlayer player2 = new JPAPlayer();
        player2.setId(Long.valueOf(2));
        player2.setName("Player2");
        player2.setGame(game);
        player2.setSquares(new TreeMap<>());
        player2.setPlayerOrder(1);
        player2.setMasterBuilder(false);
        player2.setPlayerStep(PlayerStep.WAIT);

        List<JPAPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        game.setPlayers(players);
        game.setResource(null);

        EnumMap<Piece, BuildingName> cards = new EnumMap<Piece, BuildingName>(Piece.class);
        cards.put(Piece.BLUE_BUILDING, BuildingName.COTTAGE);
        cards.put(Piece.RED_BUILDING, BuildingName.ORCHARD);
        cards.put(Piece.GREY_BUILDING, BuildingName.SHED);
        cards.put(Piece.GREEN_BUILDING, BuildingName.TAVERN);
        cards.put(Piece.YELLOW_BUILDING, BuildingName.MARKET);
        cards.put(Piece.ORANGE_BUILDING, BuildingName.CHAPEL);
        game.setCards(cards);

        return game;
    }

    private static Map<BuildingName, Building> createBuildingMap() {
        Map<BuildingName, Building> map = new EnumMap<BuildingName, Building>(BuildingName.class);
        map.put(BuildingName.TAILOR, new YellowBuilding(Piece.WHEAT, Piece.GLASS,Piece.STONE));
        map.put(BuildingName.MARKET, new YellowBuilding(Piece.WOOD, Piece.GLASS, Piece.STONE));
        return map;
    }
}
