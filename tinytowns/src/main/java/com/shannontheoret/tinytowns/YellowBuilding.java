package com.shannontheoret.tinytowns;

import java.util.HashMap;
import java.util.Map;

public class YellowBuilding extends Building {

    Piece resourceA;
    Piece resourceB;
    Piece resourceC;


    public YellowBuilding(Piece resourceA, Piece resourceB, Piece resourceC) {
        this.resourceA = resourceA;
        this.resourceB = resourceB;
        this.resourceC = resourceC;
        generateOrientations();
    }

    private void generateOrientations() {
        Map<RelativePosition, Piece> cubes = new HashMap<>();
        cubes.put(new RelativePosition(1,0), resourceA);
        cubes.put(new RelativePosition(1,1), resourceB);
        cubes.put(new RelativePosition(0,1), resourceC);
        cubes.put(new RelativePosition(2,1), resourceC);
        cubeOrientations.add(cubes);

        Map<RelativePosition, Piece> cubes90 = new HashMap<>();
        cubes90.put(new RelativePosition(1,1), resourceA);
        cubes90.put(new RelativePosition(0,1), resourceB);
        cubes90.put(new RelativePosition(0,0), resourceC);
        cubes90.put(new RelativePosition(0,2), resourceC);
        cubeOrientations.add(cubes90);

        Map<RelativePosition, Piece> cubes180 = new HashMap<>();
        cubes180.put(new RelativePosition(1,1), resourceA);
        cubes180.put(new RelativePosition(1,0), resourceB);
        cubes180.put(new RelativePosition(0,0), resourceC);
        cubes180.put(new RelativePosition(2,0), resourceC);
        cubeOrientations.add(cubes180);

        Map<RelativePosition, Piece> cubes270 = new HashMap<>();
        cubes270.put(new RelativePosition(0,1), resourceA);
        cubes270.put(new RelativePosition(1,1), resourceB);
        cubes270.put(new RelativePosition(1,0), resourceC);
        cubes270.put(new RelativePosition(1,2), resourceC);
        cubeOrientations.add(cubes270);
    }


    @Override
    Piece getPiece() {
        return Piece.YELLOW_BUILDING;
    }
}
