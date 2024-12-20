package com.shannontheoret.tinytowns;

import java.util.*;

public abstract class Building {

    List<Map<RelativePosition, Piece>> cubeOrientations = new ArrayList<>();

    abstract Piece getPiece();

    public boolean isValidBuild(Set<Integer> indexes, Map<Integer, Piece> portionOfGrid) {
        for (Integer index: indexes) {
            if (!RelativePosition.isValidIndex(index)) {
                return false;
            }
        }
        Integer startingIndex = indexes.iterator().next();
        List<Map<Integer, Piece>> validTranslations = getValidTranslations(portionOfGrid.get(startingIndex), startingIndex);
        return isValidBuild(portionOfGrid, validTranslations);
    }

    public boolean isAbleToBeConstructedAnywhere() {
        return false;
    }

    private List<Map<Integer, Piece>> getValidTranslations(Piece resource, Integer index) throws IndexOutOfBoundsException {
        RelativePosition indexRelativePosition = RelativePosition.getRelativePositionFromIndex(index);
        List<Map<Integer, Piece>> validTranslations = new ArrayList<>();
        for (Map<RelativePosition, Piece> cubeOrientation: cubeOrientations) {
            for (Map.Entry<RelativePosition, Piece> cube : cubeOrientation.entrySet()) {
                if (cube.getValue() == resource) {
                    RelativePosition shift =  cube.getKey().generateShift(indexRelativePosition);
                    Map<Integer, Piece> translation = new HashMap<>();
                    boolean validIndexes = true;
                    for (Map.Entry<RelativePosition, Piece> piece : cubeOrientation.entrySet()) {
                        RelativePosition shiftedPosition = piece.getKey().shift(shift);
                        if (shiftedPosition.getIndex() != -1) {
                            translation.put(shiftedPosition.getIndex(), piece.getValue());
                        } else {
                            validIndexes = false;
                        }
                    }
                    if (validIndexes) {
                        validTranslations.add(translation);
                    }
                }
            }
        }
        return validTranslations;
    }

    private boolean isValidBuild(Map<Integer, Piece> portionOfPlayerGrid, List<Map<Integer, Piece>> validTranslations) {
        for (Map<Integer, Piece> validTranslation : validTranslations) {
            if (portionOfPlayerGrid.equals(validTranslation)) {
                return true;
            }
        }
        return false;
    }


}
