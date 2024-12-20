package com.shannontheoret.tinytowns;

import java.util.EnumSet;

public enum Piece {
    WOOD,
    WHEAT,
    STONE,
    BRICK,
    GLASS,
    BLUE_BUILDING,
    RED_BUILDING,
    GREY_BUILDING,
    ORANGE_BUILDING,
    GREEN_BUILDING,
    YELLOW_BUILDING,
    BLACK_BUILDING
    ;

    public boolean isBuilding() {
        return getBuilding().contains(this);
    }
    public static EnumSet<Piece> getColouredBlocks() {
        return EnumSet.range(WOOD, GLASS);
    }

    public static EnumSet<Piece> getBuilding() {
        return EnumSet.range(BLUE_BUILDING, BLACK_BUILDING);
    }
}
