package com.shannontheoret.tinytowns;

import java.util.*;
import java.util.stream.Collectors;

public class GridUtility {

    public static Set<Integer> generateEightSquaresSurrounding(Integer index) {
        RelativePosition relativePosition = RelativePosition.getRelativePositionFromIndex(index);
        Set<RelativePosition> surroundingRelativePositions = new HashSet<>();
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(0,-1)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(1,-1)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(1, 0)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(1,1)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(0,1)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(-1,1)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(-1,0)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(-1,-1)));
        return surroundingRelativePositions.stream().map(rp -> rp.getIndex()).filter(i -> i>-1).collect(Collectors.toSet());
    }

    public static Set<Integer> generateRow(Integer index) {
        RelativePosition relativePosition = RelativePosition.getRelativePositionFromIndex(index);
        Integer y = relativePosition.getY();
        Set<Integer> indexesInRow = new HashSet<>();
        for(int x = 0; x < 4; x++) {
            indexesInRow.add(new RelativePosition(x, y).getIndex());
        }
        return indexesInRow;
    }

    public static Set<Integer> generateColumn(Integer index) {
        RelativePosition relativePosition = RelativePosition.getRelativePositionFromIndex(index);
        Integer x = relativePosition.getX();
        Set<Integer> indexesInRow = new HashSet<>();
        for(int y = 0; y < 4; y++) {
            indexesInRow.add(new RelativePosition(x, y).getIndex());
        }
        return indexesInRow;
    }

    public static boolean isAdjacent(Integer index1, Integer index2) {
        return generateAdjacentSquares(index1).contains(index2);
    }

    public static Set<Integer> generateAdjacentSquares(Integer index) {
        RelativePosition relativePosition = RelativePosition.getRelativePositionFromIndex(index);
        Set<RelativePosition> surroundingRelativePositions = new HashSet<>();
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(0,-1)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(1, 0)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(0,1)));
        surroundingRelativePositions.add(relativePosition.shift(new RelativePosition(-1,0)));
        return surroundingRelativePositions.stream().map(rp -> rp.getIndex()).filter(i -> i>-1).collect(Collectors.toSet());
    }

    public static List<Set<Integer>> generateContiguousGroupsSortedBySize(Set<Integer> indexes) {
        List<Set<Integer>> contiguousGroups = new ArrayList<>();
        Set<Integer> workingSet = new HashSet<>(indexes);
        Set<Integer> adjacentToCheck = new HashSet<>();
        while (!workingSet.isEmpty()) {
            Set<Integer> group = new HashSet<>();
            Integer nextIndex = workingSet.iterator().next();
            group.add(nextIndex);
            workingSet.remove(nextIndex);
            adjacentToCheck.addAll(generateAdjacentSquares(nextIndex));
            while (!adjacentToCheck.isEmpty()) {
                nextIndex = adjacentToCheck.iterator().next();
                adjacentToCheck.remove(nextIndex);
                if (workingSet.contains(nextIndex)) {
                    group.add(nextIndex);
                    workingSet.remove(nextIndex);
                    adjacentToCheck.addAll(generateAdjacentSquares(nextIndex));
                }
            }
            contiguousGroups.add(group);
        }
        contiguousGroups.sort((set1, set2) -> Integer.compare(set2.size(), set1.size()));
        return contiguousGroups;
    }

    public static Integer countNumberAdjacent(Integer index, Set<Integer> otherLocations) {
        Set<Integer> adjacent = GridUtility.generateAdjacentSquares(index);
        adjacent.retainAll(otherLocations);
        return adjacent.size();
    }

    public static Set<Integer> generateCornerIndexes() {
        return Set.of(0,3,12,15);
    }

    public static Set<Integer> getFourCenterSquares() {
        return Set.of(5,6,9,10);
    }


}
