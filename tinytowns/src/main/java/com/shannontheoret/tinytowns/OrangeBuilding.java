package com.shannontheoret.tinytowns;

import java.util.*;

public class OrangeBuilding extends Building {

    Piece resourceA;
    Piece resourceB;
    Piece resourceC;
    Piece resourceD;


    public OrangeBuilding(Piece resourceA, Piece resourceB, Piece resourceC, Piece resourceD) {
        this.resourceA = resourceA;
        this.resourceB = resourceB;
        this.resourceC = resourceC;
        this.resourceD = resourceD;
        generateOrientations();
    }

    private void generateOrientations() {
        Map<RelativePosition, Piece> cubes = new HashMap<>();
        cubes.put(new RelativePosition(2, 0), resourceA);
        cubes.put(new RelativePosition(0, 1), resourceB);
        cubes.put(new RelativePosition(1, 1), resourceC);
        cubes.put(new RelativePosition(2, 1), resourceD);
        cubeOrientations.add(cubes);

        Map<RelativePosition, Piece> cubesMirrorX = new HashMap<>();
        cubesMirrorX.put(new RelativePosition(0, 0), resourceA);
        cubesMirrorX.put(new RelativePosition(2, 1), resourceB);
        cubesMirrorX.put(new RelativePosition(1, 1), resourceC);
        cubesMirrorX.put(new RelativePosition(0, 1), resourceD);
        cubeOrientations.add(cubesMirrorX);

        Map<RelativePosition, Piece> cubesMirrorY = new HashMap<>();
        cubesMirrorY.put(new RelativePosition(2, 1), resourceA);
        cubesMirrorY.put(new RelativePosition(0, 0), resourceB);
        cubesMirrorY.put(new RelativePosition(1, 0), resourceC);
        cubesMirrorY.put(new RelativePosition(2, 0), resourceD);
        cubeOrientations.add(cubesMirrorY);

        Map<RelativePosition, Piece> cubes90 = new HashMap<>();
        cubes90.put(new RelativePosition(2, 2), resourceA);
        cubes90.put(new RelativePosition(1, 0), resourceB);
        cubes90.put(new RelativePosition(1, 1), resourceC);
        cubes90.put(new RelativePosition(1, 2), resourceD);
        cubeOrientations.add(cubes90);

        Map<RelativePosition, Piece> cubes90MirrorX = new HashMap<>();
        cubes90MirrorX.put(new RelativePosition(0, 2), resourceA);
        cubes90MirrorX.put(new RelativePosition(1, 0), resourceB);
        cubes90MirrorX.put(new RelativePosition(1, 1), resourceC);
        cubes90MirrorX.put(new RelativePosition(1, 2), resourceD);
        cubeOrientations.add(cubes90MirrorX);

        Map<RelativePosition, Piece> cubes90MirrorY = new HashMap<>();
        cubes90MirrorY.put(new RelativePosition(1, 0), resourceA);
        cubes90MirrorY.put(new RelativePosition(0, 2), resourceB);
        cubes90MirrorY.put(new RelativePosition(0, 1), resourceC);
        cubes90MirrorY.put(new RelativePosition(0, 0), resourceD);
        cubeOrientations.add(cubes90MirrorY);

        Map<RelativePosition, Piece> cubes180 = new HashMap<>();
        cubes180.put(new RelativePosition(0, 2), resourceA);
        cubes180.put(new RelativePosition(2, 1), resourceB);
        cubes180.put(new RelativePosition(1, 1), resourceC);
        cubes180.put(new RelativePosition(0, 1), resourceD);
        cubeOrientations.add(cubes180);

        Map<RelativePosition, Piece> cubes270 = new HashMap<>();
        cubes270.put(new RelativePosition(0, 0), resourceA);
        cubes270.put(new RelativePosition(1, 2), resourceB);
        cubes270.put(new RelativePosition(1, 1), resourceC);
        cubes270.put(new RelativePosition(1, 0), resourceD);
        cubeOrientations.add(cubes270);
    }



    @Override
    public Piece getPiece() {
        return Piece.ORANGE_BUILDING;
    }
}
