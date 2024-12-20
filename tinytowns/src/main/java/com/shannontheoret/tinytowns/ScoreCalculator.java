package com.shannontheoret.tinytowns;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private static final Integer FARM_FEEDS = 4;
    private static final Integer COTTAGE_SCORE = 3;
    private static final Integer ABBEY_SCORE = 3;
    private static final Integer TEMPLE_SCORE = 4;
    private static final Integer TEMPLE_NUMBER_OF_ADJACENT= 2;
    private static final Integer FOUNTAIN_SCORE = 2;
    private static final Integer MILLSTONE_SCORE = 2;
    private static final Integer BAKERY_SCORE = 3;
    private static final Integer TAILOR_SCORE = 1;
    private static final Integer FEAST_HALL_BASE_SCORE = 2;
    private static final Integer FEAST_HALL_BONUS_SCORE = 1;
    private static final Integer INN_SCORE = 3;

    private static final Map<Integer, Integer> TAVERN_SCORE_MAP = new HashMap<>();

    static {
        TAVERN_SCORE_MAP.put(1,2);
        TAVERN_SCORE_MAP.put(2,5);
        TAVERN_SCORE_MAP.put(3,9);
        TAVERN_SCORE_MAP.put(4,14);
        TAVERN_SCORE_MAP.put(5,20);
    }

    private static final Map<Integer, Integer> ALMSHOUSE_SCORE_MAP = new HashMap<>();

    static {
        ALMSHOUSE_SCORE_MAP.put(1, -1);
        ALMSHOUSE_SCORE_MAP.put(2,5);
        ALMSHOUSE_SCORE_MAP.put(3,-3);
        ALMSHOUSE_SCORE_MAP.put(4,15);
        ALMSHOUSE_SCORE_MAP.put(5,-5);
        ALMSHOUSE_SCORE_MAP.put(6,26);
    }

    private Map<Piece, BuildingName> cards;
    private Map<Integer, Piece> grid;

    private Integer playerToRightNumberOfFeastHalls = 0;

    private Set<Integer> fedCottages = new HashSet<>();

    public ScoreCalculator(Map<Piece, BuildingName> cards, Map<Integer, Piece> grid) {
        this.cards = cards;
        this.grid = grid;
    }

    public void setPlayerToRightNumberOfFeastHalls(Integer playerToRightNumberOfFeastHalls) {
        this.playerToRightNumberOfFeastHalls=playerToRightNumberOfFeastHalls;
    }

    public Map<Piece, Integer> calculateScore() {
        Map<Piece, Integer> score = new EnumMap<Piece, Integer>(Piece.class);
        score.put(Piece.BLUE_BUILDING, calculateBlueBuildings());
        score.put(Piece.ORANGE_BUILDING, calculateOrangeBuildings());
        score.put(Piece.GREY_BUILDING, calculateGreyBuildings());
        score.put(Piece.YELLOW_BUILDING, calculateYellowBuildings());
        score.put(Piece.GREEN_BUILDING, calculateGreenBuildings());
        return score;
    }

    public Integer calculatePenalty() {
        Integer numberOfBuildings = this.grid.values().stream().filter(piece -> piece.isBuilding()).collect(Collectors.toList()).size();
        return 16 - numberOfBuildings;
    }


    private Integer calculateBlueBuildings() {
        BuildingName redBuilding = cards.get(Piece.RED_BUILDING);
        Set<Integer> redLocations = getPieceLocations(Piece.RED_BUILDING);
        Set<Integer> blueLocations = getPieceLocations(Piece.BLUE_BUILDING);
        switch (redBuilding) {
            case GRANARY:
                for (Integer redLocation : redLocations) {
                  Set<Integer> surroundingSquares = GridUtility.generateEightSquaresSurrounding(redLocation);
                  for (Integer surroundingSquare : surroundingSquares) {
                      if (blueLocations.contains(surroundingSquare)) {
                          fedCottages.add(surroundingSquare);
                      }
                  }
                }
                break;
            case ORCHARD:
                for (Integer redLocation: redLocations) {
                    Set<Integer> row = GridUtility.generateRow(redLocation);
                    Set<Integer> column = GridUtility.generateColumn(redLocation);
                    for(Integer blueLocation: blueLocations) {
                        if (row.contains(blueLocation) || column.contains(blueLocation)) {
                            fedCottages.add(blueLocation);
                        }
                    }
                }
                break;
            case FARM:
                Integer maxFedCottages = FARM_FEEDS * redLocations.size();
                if (cards.get(Piece.ORANGE_BUILDING) == BuildingName.TEMPLE && blueLocations.size() > maxFedCottages) {
                    Set<Integer> orangeBuildings = getPieceLocations(Piece.ORANGE_BUILDING);
                    Set<Integer> adjacentCottages = new HashSet<>();
                    for (Integer temple: orangeBuildings) {
                        for (Integer cottage : blueLocations) {
                            if (GridUtility.isAdjacent(temple, cottage)) {
                                adjacentCottages.add(cottage);
                            }
                        }
                    }
                    Iterator<Integer> adjacentCottagesIterator = adjacentCottages.stream().iterator();
                    while (adjacentCottagesIterator.hasNext() && fedCottages.size() < maxFedCottages) {
                        fedCottages.add(adjacentCottagesIterator.next());
                    }
                }
                Iterator<Integer> blueLocationIterator = blueLocations.iterator();
                while (blueLocationIterator.hasNext() && fedCottages.size() < maxFedCottages) {
                    fedCottages.add(blueLocationIterator.next());
                }
                break;
            case GREENHOUSE:
                Integer numberOfContiguousGroupsToScore = redLocations.size();
                List<Set<Integer>> contiguousGroups = GridUtility.generateContiguousGroupsSortedBySize(blueLocations);
                for (int i=0; i < redLocations.size(); i++) {
                    fedCottages.addAll(contiguousGroups.get(i));
                }
                break;
        }
        return fedCottages.size() * COTTAGE_SCORE;
    }

    private Integer calculateOrangeBuildings() {
        Integer score = 0;
        BuildingName buildingName = cards.get(Piece.ORANGE_BUILDING);
        Set<Integer> orangeLocations = getPieceLocations(Piece.ORANGE_BUILDING);
        switch (buildingName) {
            case ABBEY:
                for (Integer location: orangeLocations) {
                    Set<Integer> adjacentLocations = GridUtility.generateAdjacentSquares(location);
                    if(!adjacentLocations.stream().anyMatch(i -> (grid.containsKey(i) && (grid.get(i) == Piece.GREEN_BUILDING || grid.get(i) == Piece.YELLOW_BUILDING)))) {
                        score += ABBEY_SCORE;
                    }
                }
                break;
            case CLOISTER:
                Integer cloistersInCorner = GridUtility.generateCornerIndexes().stream().filter(corner -> orangeLocations.contains(corner)).collect(Collectors.toSet()).size();
                score = orangeLocations.size() * cloistersInCorner;
                break;
            case CHAPEL:
                score = fedCottages.size() *  orangeLocations.size();
                break;
            case TEMPLE:
                for (Integer location: orangeLocations) {
                    if (GridUtility.countNumberAdjacent(location, fedCottages) >= TEMPLE_NUMBER_OF_ADJACENT) {
                        score += TEMPLE_SCORE;
                    }
                }
                break;
        }
        return score;
    }

    private Integer calculateGreyBuildings() {
        Integer score = 0;
        BuildingName buildingName = cards.get(Piece.GREY_BUILDING);
        Set<Integer> greyLocations = getPieceLocations(Piece.GREY_BUILDING);
        switch (buildingName) {
            case FOUNTAIN:
                for (Integer greyLocation : greyLocations) {
                    if(GridUtility.countNumberAdjacent(greyLocation, greyLocations) > 0) {
                        score += FOUNTAIN_SCORE;
                    }
                }
                break;
            case WELL:
                for (Integer greyLocation: greyLocations) {
                    score += GridUtility.countNumberAdjacent(greyLocation, getPieceLocations(Piece.BLUE_BUILDING));
                }
                break;
            case SHED:
                score = greyLocations.size();
                break;
            case MILLSTONE:
                Set<Integer> redLocations = getPieceLocations(Piece.RED_BUILDING);
                Set<Integer> yellowLocations = getPieceLocations(Piece.YELLOW_BUILDING);
                for (Integer location: greyLocations) {
                    if (GridUtility.countNumberAdjacent(location, redLocations) >= 1 ||
                        GridUtility.countNumberAdjacent(location, yellowLocations) >= 1) {
                        score += MILLSTONE_SCORE;
                    }
                }
                break;
        }
        return score;
    }

    private Integer calculateYellowBuildings() {
        Integer score = 0;
        BuildingName buildingName = cards.get(Piece.YELLOW_BUILDING);
        Set<Integer> yellowLocations = getPieceLocations(Piece.YELLOW_BUILDING);
        switch (buildingName) {
            case BAKERY:
                Set<Integer> redLocations = getPieceLocations(Piece.RED_BUILDING);
                for (Integer location: yellowLocations) {
                    if( GridUtility.countNumberAdjacent(location, redLocations) >= 1) {
                        score += BAKERY_SCORE;
                    }
                }
                break;
            case TAILOR:
                Integer tailorsInCenter = GridUtility.getFourCenterSquares().stream().filter(corner -> yellowLocations.contains(corner)).collect(Collectors.toSet()).size();
                score += yellowLocations.size() * (TAILOR_SCORE + tailorsInCenter);
                break;
            case MARKET:
                for (Integer location: yellowLocations) {
                    Integer tailorsInRow = GridUtility.generateRow(location).stream().filter(rowLocation -> yellowLocations.contains(rowLocation)).collect(Collectors.toSet()).size();
                    Integer tailorsInColumn = GridUtility.generateColumn(location).stream().filter(rowLocation -> yellowLocations.contains(rowLocation)).collect(Collectors.toSet()).size();
                    Integer tailorScore = (tailorsInRow > tailorsInColumn? tailorsInRow : tailorsInColumn);
                    score += tailorScore;
                }
                break;
            case THEATER:
                for (Integer location: yellowLocations) {
                    Set<Piece> uniqueBuildingTypes = new HashSet<>();
                    for (Integer rowLocation : GridUtility.generateRow(location)) {
                        if (grid.containsKey(rowLocation) && grid.get(rowLocation).isBuilding()) {
                            uniqueBuildingTypes.add(grid.get(rowLocation));
                        }
                    }
                    for (Integer columnLocation : GridUtility.generateColumn(location)) {
                        if (grid.containsKey(columnLocation) && grid.get(columnLocation).isBuilding()) {
                            uniqueBuildingTypes.add(grid.get(columnLocation));
                        }
                    }
                    score += uniqueBuildingTypes.size() - 1;
                }
                break;
        }
        return score;
    }

    private Integer calculateGreenBuildings() {
        Integer score = 0;
        BuildingName buildingName = cards.get(Piece.GREEN_BUILDING);
        Set<Integer> greenLocations = getPieceLocations(Piece.GREEN_BUILDING);
        switch (buildingName) {
            case FEAST_HALL:
                Integer scoreMultiplier = FEAST_HALL_BASE_SCORE;
                if (greenLocations.size() > playerToRightNumberOfFeastHalls) {
                    scoreMultiplier += FEAST_HALL_BONUS_SCORE;
                }
                score = scoreMultiplier * greenLocations.size();
                break;
            case TAVERN:
                if (greenLocations.size() > 5) {
                    score = TAVERN_SCORE_MAP.get(5);
                } else if (greenLocations.size() == 0) {
                    score = 0;
                } else {
                    score = TAVERN_SCORE_MAP.get(greenLocations.size());
                }
                break;
            case ALMSHOUSE:
                if (greenLocations.size() > 6) {
                    score = ALMSHOUSE_SCORE_MAP.get(6);
                } else if (greenLocations.size() == 0) {
                    score = 0;
                } else {
                    score = ALMSHOUSE_SCORE_MAP.get(greenLocations.size());
                }
                break;
            case INN:
                for (Integer location: greenLocations) {
                    if (GridUtility.generateColumn(location).stream().filter(columnSquare -> greenLocations.contains(columnSquare)).collect(Collectors.toSet()).size()==1
                    && GridUtility.generateRow(location).stream().filter(rowSquare -> greenLocations.contains(rowSquare)).collect(Collectors.toSet()).size()==1) {
                        score += INN_SCORE;
                    }
                }
        }
        return score;
    }

    private Set<Integer> getPieceLocations(Piece piece) {
        Set<Integer> matchingIndexes = grid.entrySet().stream()
                .filter(entry -> entry.getValue() == piece)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        return matchingIndexes;
    }

}
