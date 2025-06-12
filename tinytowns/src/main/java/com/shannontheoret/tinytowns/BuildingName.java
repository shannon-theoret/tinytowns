package com.shannontheoret.tinytowns;

import java.util.EnumSet;

public enum BuildingName {
        WAREHOUSE(Piece.BLACK_BUILDING),
        TRADING_POST(Piece.BLACK_BUILDING),
        FACTORY(Piece.BLACK_BUILDING),
        BANK(Piece.BLACK_BUILDING),
        THEATER(Piece.YELLOW_BUILDING),
        TAILOR(Piece.YELLOW_BUILDING),
        MARKET(Piece.YELLOW_BUILDING),
        BAKERY(Piece.YELLOW_BUILDING),
        TAVERN(Piece.GREEN_BUILDING),
        INN(Piece.GREEN_BUILDING),
        FEAST_HALL(Piece.GREEN_BUILDING),
        ALMSHOUSE(Piece.GREEN_BUILDING),
        TEMPLE(Piece.ORANGE_BUILDING),
        CLOISTER(Piece.ORANGE_BUILDING),
        CHAPEL(Piece.ORANGE_BUILDING),
        ABBEY(Piece.ORANGE_BUILDING),
        WELL(Piece.GREY_BUILDING),
        SHED(Piece.GREY_BUILDING),
        MILLSTONE(Piece.GREY_BUILDING),
        FOUNTAIN(Piece.GREY_BUILDING),
        ORCHARD(Piece.RED_BUILDING),
        GREENHOUSE(Piece.RED_BUILDING),
        GRANARY(Piece.RED_BUILDING),
        FARM(Piece.RED_BUILDING),
        COTTAGE(Piece.BLUE_BUILDING);

        private final Piece color;

        BuildingName(Piece color) {
                this.color = color;
        }

        public Piece getColor() {
                return this.color;
        }

        public static EnumSet<BuildingName> getBuildingsByColor(Piece color) {
                EnumSet<BuildingName> buildings = EnumSet.noneOf(BuildingName.class);
                for (BuildingName building : BuildingName.values()) {
                        if (building.getColor() == color) {
                                buildings.add(building);
                        }
                }
                return buildings;
        }
}
