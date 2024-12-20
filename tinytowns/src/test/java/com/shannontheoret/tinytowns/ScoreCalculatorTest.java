package com.shannontheoret.tinytowns;


import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCalculatorTest {

    Map<Integer, Piece> gridA = new HashMap<>();
    Map<Integer, Piece> gridB = new HashMap<>();
    Map<Piece, BuildingName> cardsA = new EnumMap<>(Piece.class);
    Map<Piece, BuildingName> cardsB = new EnumMap<>(Piece.class);
    Map<Piece, Integer> scoreAA = new EnumMap<Piece, Integer>(Piece.class);
    Map<Piece, Integer> scoreAB = new EnumMap<Piece, Integer>(Piece.class);
    Map<Piece, Integer> scoreBA = new EnumMap<Piece, Integer>(Piece.class);
    Map<Piece, Integer> scoreBB = new EnumMap<Piece, Integer>(Piece.class);


    @Test
    public void calculateScoreTestA() {
        Map<Piece, BuildingName> cards = new EnumMap<Piece, BuildingName>(Piece.class);
        cards.put(Piece.BLUE_BUILDING, BuildingName.COTTAGE);
        cards.put(Piece.YELLOW_BUILDING, BuildingName.MARKET);
        cards.put(Piece.RED_BUILDING, BuildingName.GREENHOUSE);
        cards.put(Piece.GREY_BUILDING, BuildingName.MILLSTONE);
        cards.put(Piece.GREEN_BUILDING, BuildingName.TAVERN);
        cards.put(Piece.ORANGE_BUILDING, BuildingName.ABBEY);

        Map<Integer, Piece> grid = new HashMap<>();
        grid.put(0, Piece.GREY_BUILDING);
        grid.put(1, Piece.YELLOW_BUILDING);
        grid.put(2, Piece.GREEN_BUILDING);
        grid.put(3, Piece.BLUE_BUILDING);
        grid.put(4, Piece.ORANGE_BUILDING);
        grid.put(5, Piece.YELLOW_BUILDING);
        grid.put(6, Piece.YELLOW_BUILDING);
        grid.put(7, Piece.YELLOW_BUILDING);
        grid.put(8, Piece.BLUE_BUILDING);
        grid.put(9, Piece.WOOD);
        grid.put(10, Piece.ORANGE_BUILDING);
        grid.put(11, Piece.RED_BUILDING);
        grid.put(12, Piece.BLUE_BUILDING);
        grid.put(13, Piece.GREEN_BUILDING);
        grid.put(14,Piece.GREY_BUILDING);
        grid.put(15, Piece.ORANGE_BUILDING);

        ScoreCalculator scoreCalculator = new ScoreCalculator(cards, grid);
        Map<Piece, Integer> score = scoreCalculator.calculateScore();

        assertEquals(6, score.get(Piece.BLUE_BUILDING));
        assertEquals(11, score.get(Piece.YELLOW_BUILDING));
        assertEquals(2, score.get(Piece.GREY_BUILDING));
        assertEquals(5, score.get(Piece.GREEN_BUILDING));
        assertEquals(3, score.get(Piece.ORANGE_BUILDING));
        assertEquals(1, scoreCalculator.calculatePenalty());
    }

    @Test
    public void calculateScoreTestB() {
        Map<Piece, BuildingName> cards = new EnumMap<Piece, BuildingName>(Piece.class);
        cards.put(Piece.BLUE_BUILDING, BuildingName.COTTAGE);
        cards.put(Piece.RED_BUILDING, BuildingName.GRANARY);
        cards.put(Piece.ORANGE_BUILDING, BuildingName.CLOISTER);
        cards.put(Piece.GREEN_BUILDING, BuildingName.ALMSHOUSE);
        cards.put(Piece.GREY_BUILDING, BuildingName.SHED);
        cards.put(Piece.YELLOW_BUILDING, BuildingName.THEATER);

        Map<Integer, Piece> grid = new HashMap<>();
        grid.put(0, Piece.ORANGE_BUILDING);
        grid.put(1, Piece.STONE);
        grid.put(2, Piece.GREEN_BUILDING);
        grid.put(3, Piece.ORANGE_BUILDING);
        grid.put(4, Piece.GREY_BUILDING);
        grid.put(5, Piece.ORANGE_BUILDING);
        grid.put(6, Piece.GREY_BUILDING);
        grid.put(7, Piece.YELLOW_BUILDING);
        grid.put(8,Piece.GREEN_BUILDING);
        grid.put(9, Piece.WOOD);
        grid.put(10,Piece.BLUE_BUILDING);
        grid.put(11, Piece.BLUE_BUILDING);
        grid.put(12, Piece.GLASS);
        grid.put(13, Piece.GREEN_BUILDING);
        grid.put(14, Piece.WHEAT);
        grid.put(15, Piece.RED_BUILDING);

        ScoreCalculator scoreCalculator = new ScoreCalculator(cards, grid);
        Map<Piece, Integer> score = scoreCalculator.calculateScore();

        assertEquals(6, score.get(Piece.BLUE_BUILDING));
        assertEquals(4, score.get(Piece.YELLOW_BUILDING));
        assertEquals(2, score.get(Piece.GREY_BUILDING));
        assertEquals(-3, score.get(Piece.GREEN_BUILDING));
        assertEquals(6, score.get(Piece.ORANGE_BUILDING));
        assertEquals(4, scoreCalculator.calculatePenalty());
    }

    @Test
    public void calculateScoreTestC() {
        Map<Piece, BuildingName> cards = new EnumMap<Piece, BuildingName>(Piece.class);
        cards.put(Piece.BLUE_BUILDING, BuildingName.COTTAGE);
        cards.put(Piece.RED_BUILDING, BuildingName.ORCHARD);
        cards.put(Piece.ORANGE_BUILDING, BuildingName.CHAPEL);
        cards.put(Piece.GREEN_BUILDING,BuildingName.FEAST_HALL);
        cards.put(Piece.GREY_BUILDING, BuildingName.FOUNTAIN);
        cards.put(Piece.YELLOW_BUILDING, BuildingName.TAILOR);

        Map<Integer, Piece> grid = new HashMap<>();
        grid.put(0, Piece.GREY_BUILDING);
        grid.put(1, Piece.BLUE_BUILDING);
        grid.put(2, Piece.WOOD);
        grid.put(3, Piece.RED_BUILDING);
        grid.put(4, Piece.ORANGE_BUILDING);
        grid.put(5, Piece.YELLOW_BUILDING);
        grid.put(6, Piece.GREEN_BUILDING);
        grid.put(7, Piece.BLUE_BUILDING);
        grid.put(8,Piece.BLUE_BUILDING);
        grid.put(9,Piece.GREEN_BUILDING);
        grid.put(10,Piece.YELLOW_BUILDING);
        grid.put(11, Piece.GREEN_BUILDING);
        grid.put(12, Piece.GREEN_BUILDING);
        grid.put(13, Piece.GREY_BUILDING);
        grid.put(14, Piece.GREY_BUILDING);
        grid.put(15, Piece.ORANGE_BUILDING);

        ScoreCalculator scoreCalculator = new ScoreCalculator(cards, grid);
        scoreCalculator.setPlayerToRightNumberOfFeastHalls(4);
        Map<Piece, Integer> score = scoreCalculator.calculateScore();


        assertEquals(6, score.get(Piece.BLUE_BUILDING));
        assertEquals(6, score.get(Piece.YELLOW_BUILDING));
        assertEquals(4, score.get(Piece.GREY_BUILDING));
        assertEquals(8, score.get(Piece.GREEN_BUILDING));
        assertEquals(4, score.get(Piece.ORANGE_BUILDING));
        assertEquals(1, scoreCalculator.calculatePenalty());
    }

    @Test
    public void calculateScoreTestD() {
        Map<Piece, BuildingName> cards = new EnumMap<Piece, BuildingName>(Piece.class);
        cards.put(Piece.BLUE_BUILDING, BuildingName.COTTAGE);
        cards.put(Piece.RED_BUILDING, BuildingName.FARM);
        cards.put(Piece.GREEN_BUILDING, BuildingName.INN);
        cards.put(Piece.ORANGE_BUILDING, BuildingName.TEMPLE);
        cards.put(Piece.YELLOW_BUILDING, BuildingName.BAKERY);
        cards.put(Piece.GREY_BUILDING, BuildingName.WELL);

        Map<Integer, Piece> grid = new HashMap<>();
        grid.put(0, Piece.RED_BUILDING);
        grid.put(1, Piece.BLUE_BUILDING);
        grid.put(2, Piece.GREY_BUILDING);
        grid.put(3, Piece.BLUE_BUILDING);
        grid.put(4, Piece.GREEN_BUILDING);
        grid.put(5, Piece.ORANGE_BUILDING);
        grid.put(6, Piece.BLUE_BUILDING);
        grid.put(7, Piece.ORANGE_BUILDING);
        grid.put(8,Piece.BLUE_BUILDING);
        grid.put(9,Piece.YELLOW_BUILDING);
        grid.put(10,Piece.GREEN_BUILDING);
        grid.put(11, Piece.GREEN_BUILDING);
        grid.put(12, Piece.GREY_BUILDING);
        grid.put(13, Piece.YELLOW_BUILDING);
        grid.put(14, Piece.WHEAT);
        grid.put(15, Piece.BLUE_BUILDING);

        ScoreCalculator scoreCalculator = new ScoreCalculator(cards, grid);
        Map<Piece, Integer> score = scoreCalculator.calculateScore();

        assertEquals(12, score.get(Piece.BLUE_BUILDING));
        assertEquals(0, score.get(Piece.YELLOW_BUILDING));
        assertEquals(4, score.get(Piece.GREY_BUILDING));
        assertEquals(3, score.get(Piece.GREEN_BUILDING));
        assertEquals(8, score.get(Piece.ORANGE_BUILDING));
        assertEquals(1, scoreCalculator.calculatePenalty());
    }

}
