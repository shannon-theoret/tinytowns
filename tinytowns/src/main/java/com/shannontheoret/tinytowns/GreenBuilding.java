package com.shannontheoret.tinytowns;

import java.util.HashMap;
import java.util.Map;

public class GreenBuilding extends Building {

    Piece resourceA;
    Piece resourceB;
    Piece resourceC;


    public GreenBuilding(Piece resourceA, Piece resourceB, Piece resourceC) {
        this.resourceA = resourceA;
        this.resourceB = resourceB;
        this.resourceC = resourceC;
        generateOrientations();
    }

    private void generateOrientations() {
        Map<RelativePosition, Piece> cubes = new HashMap<>();
        cubes.put(new RelativePosition(0,0), resourceA);
        cubes.put(new RelativePosition(1,0), resourceB);
        cubes.put(new RelativePosition(2,0), resourceC);
        cubeOrientations.add(cubes);

        Map<RelativePosition, Piece> cubesMirror = new HashMap<>();
        cubesMirror.put(new RelativePosition(2,0), resourceA);
        cubesMirror.put(new RelativePosition(1,0), resourceB);
        cubesMirror.put(new RelativePosition(0,0), resourceC);
        cubeOrientations.add(cubesMirror);

        Map<RelativePosition, Piece> cubesVertical = new HashMap<>();
        cubesVertical.put(new RelativePosition(0,0), resourceA);
        cubesVertical.put(new RelativePosition(0,1), resourceB);
        cubesVertical.put(new RelativePosition(0,2), resourceC);
        cubeOrientations.add(cubesVertical);

        Map<RelativePosition, Piece> cubesVerticalMirror = new HashMap<>();
        cubesVerticalMirror.put(new RelativePosition(0,2), resourceA);
        cubesVerticalMirror.put(new RelativePosition(0,1), resourceB);
        cubesVerticalMirror.put(new RelativePosition(0,0), resourceC);
        cubeOrientations.add(cubesVerticalMirror);
    }



    @Override
    public Piece getPiece() {
        return Piece.GREEN_BUILDING;
    }

}
