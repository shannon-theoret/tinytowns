package com.shannontheoret.tinytowns;

import java.util.HashMap;
import java.util.Map;

public class GreyBuilding extends Building {

    boolean ableToBeConstructedAnywhere;
    public GreyBuilding(boolean ableToBeConstructedAnywhere) {
        this.ableToBeConstructedAnywhere = ableToBeConstructedAnywhere;
        generateOrientations();
    }

    private void generateOrientations() {
        Map<RelativePosition, Piece> cubes = new HashMap<>();
        cubes.put(new RelativePosition(0, 0), Piece.WOOD);
        cubes.put(new RelativePosition(1, 0), Piece.STONE);
        cubeOrientations.add(cubes);

        Map<RelativePosition, Piece> cubesMirror = new HashMap<>();
        cubesMirror.put(new RelativePosition(1, 0), Piece.WOOD);
        cubesMirror.put(new RelativePosition(0, 0), Piece.STONE);
        cubeOrientations.add(cubesMirror);

        Map<RelativePosition, Piece> cubesVertical = new HashMap<>();
        cubesVertical.put(new RelativePosition(0, 0), Piece.WOOD);
        cubesVertical.put(new RelativePosition(0, 1), Piece.STONE);
        cubeOrientations.add(cubesVertical);

        Map<RelativePosition, Piece> cubesVerticalMirror = new HashMap<>();
        cubesVerticalMirror.put(new RelativePosition(0, 1), Piece.WOOD);
        cubesVerticalMirror.put(new RelativePosition(0, 0), Piece.STONE);
        cubeOrientations.add(cubesVerticalMirror);
    }


    @Override
    Piece getPiece() {
        return Piece.GREY_BUILDING;
    }

    @Override
    public boolean isAbleToBeConstructedAnywhere() {
        return ableToBeConstructedAnywhere;
    }
}
