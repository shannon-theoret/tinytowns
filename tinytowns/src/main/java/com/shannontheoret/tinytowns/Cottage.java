package com.shannontheoret.tinytowns;

import java.util.HashMap;
import java.util.Map;

public class Cottage extends Building {

    public Cottage() {
        generateOrientations();
    }

    @Override
    Piece getPiece() {
        return Piece.BLUE_BUILDING;
    }

    private void generateOrientations() {
        Map<RelativePosition, Piece> cubesA = new HashMap<>();
        cubesA.put(new RelativePosition(1, 0), Piece.WHEAT);
        cubesA.put(new RelativePosition(0, 1), Piece.BRICK);
        cubesA.put(new RelativePosition(1, 1), Piece.GLASS);
        cubeOrientations.add(cubesA);

        Map<RelativePosition, Piece> cubesB = new HashMap<>();
        cubesB.put(new RelativePosition(0, 1), Piece.WHEAT);
        cubesB.put(new RelativePosition(1, 0), Piece.BRICK);
        cubesB.put(new RelativePosition(1, 1), Piece.GLASS);
        cubeOrientations.add(cubesB);

        Map<RelativePosition, Piece> cubesC = new HashMap<>();
        cubesC.put(new RelativePosition(1, 1), Piece.WHEAT);
        cubesC.put(new RelativePosition(0, 0), Piece.BRICK);
        cubesC.put(new RelativePosition(0, 1), Piece.GLASS);
        cubeOrientations.add(cubesC);

        Map<RelativePosition, Piece> cubesD = new HashMap<>();
        cubesD.put(new RelativePosition(0, 0), Piece.WHEAT);
        cubesD.put(new RelativePosition(1, 1), Piece.BRICK);
        cubesD.put(new RelativePosition(0, 1), Piece.GLASS);
        cubeOrientations.add(cubesD);

        Map<RelativePosition, Piece> cubesE = new HashMap<>();
        cubesE.put(new RelativePosition(1, 1), Piece.WHEAT);
        cubesE.put(new RelativePosition(0, 0), Piece.BRICK);
        cubesE.put(new RelativePosition(1, 0), Piece.GLASS);
        cubeOrientations.add(cubesE);

        Map<RelativePosition, Piece> cubesF = new HashMap<>();
        cubesF.put(new RelativePosition(0, 0), Piece.WHEAT);
        cubesF.put(new RelativePosition(1, 1), Piece.BRICK);
        cubesF.put(new RelativePosition(1, 0), Piece.GLASS);
        cubeOrientations.add(cubesF);

        Map<RelativePosition, Piece> cubesG = new HashMap<>();
        cubesG.put(new RelativePosition(0, 1), Piece.WHEAT);
        cubesG.put(new RelativePosition(1, 0), Piece.BRICK);
        cubesG.put(new RelativePosition(0, 0), Piece.GLASS);
        cubeOrientations.add(cubesG);

        Map<RelativePosition, Piece> cubesH = new HashMap<>();
        cubesH.put(new RelativePosition(1, 0), Piece.WHEAT);
        cubesH.put(new RelativePosition(0, 1), Piece.BRICK);
        cubesH.put(new RelativePosition(0, 0), Piece.GLASS);
        cubeOrientations.add(cubesH);
    }

}
