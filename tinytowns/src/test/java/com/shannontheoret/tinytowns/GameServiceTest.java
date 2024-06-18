package com.shannontheoret.tinytowns;

import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

public class GameServiceTest {

    private Map<Integer, Piece> sampleGrid;

    @BeforeEach
    public void setup() {
        sampleGrid = new HashMap<>();
        sampleGrid.put(1, Piece.STONE);
        sampleGrid.put(2, Piece.GLASS);
        sampleGrid.put(5, Piece.GREEN_BUILDING);
        sampleGrid.put(8, Piece.GREY_BUILDING);
        sampleGrid.put(9, Piece.BLUE_BUILDING);
        sampleGrid.put(13, Piece.GLASS);
        sampleGrid.put(14, Piece.WHEAT);
    }
}
