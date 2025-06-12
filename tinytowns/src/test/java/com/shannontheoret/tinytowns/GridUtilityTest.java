package com.shannontheoret.tinytowns;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GridUtilityTest {
    @Test
    public void generateEightSquaresSurrounding_middlePosition() {
        Integer index = 9;

        Set<Integer> surrounding = GridUtility.generateEightSquaresSurrounding(index);

        assert(surrounding.contains(4));
        assert(surrounding.contains(5));
        assert(surrounding.contains(6));
        assert(surrounding.contains(8));
        assert(surrounding.contains(10));
        assert(surrounding.contains(12));
        assert(surrounding.contains(13));
        assert(surrounding.contains(14));
        assertEquals(8, surrounding.size());
    }

    @Test
    public void generateEightSquaresSurrounding_edgePosition() {
        Integer index = 8;

        Set<Integer> surrounding = GridUtility.generateEightSquaresSurrounding(index);

        assert(surrounding.contains(4));
        assert(surrounding.contains(5));
        assert(surrounding.contains(9));
        assert(surrounding.contains(12));
        assert(surrounding.contains(13));
        assertEquals(5, surrounding.size());
    }

    @Test
    public void generateRow() {
        Integer index = 14;

        Set<Integer> row = GridUtility.generateRow(index);

        assert(row.contains(12));
        assert(row.contains(13));
        assert(row.contains(14));
        assert(row.contains(15));
        assertEquals(4, row.size());
    }

    @Test
    public void generateColumn() {
        Integer index = 2;

        Set<Integer> column = GridUtility.generateColumn(index);

        assert(column.contains(2));
        assert(column.contains(6));
        assert(column.contains(10));
        assert(column.contains(14));
    }

    @Test
    public void isAdjacent_above() {
        assertTrue(GridUtility.isAdjacent(6,2));
    }

    @Test
    public void isAdjacent_right() {
        assertTrue(GridUtility.isAdjacent(9,10));
    }

    @Test
    public void isAdjacent_below() {
        assertTrue(GridUtility.isAdjacent(0,4));
    }

    @Test
    public void isAdjacent_left() {
        assertTrue(GridUtility.isAdjacent(9,13));
    }

    @Test
    public void isAdjacent_false() {
        assertFalse(GridUtility.isAdjacent(8,5));
    }

    @Test
    public void generateContiguousGroups() {
        Set<Integer> indexes = Set.of(0,8,10,11,12,13);

        List<Set<Integer>> contiguousGroups = GridUtility.generateContiguousGroupsSortedBySize(indexes);
        assertTrue(contiguousGroups.get(0).equals(Set.of(8,12,13)));
        assertTrue(contiguousGroups.get(1).equals(Set.of(10,11)));
        assertTrue(contiguousGroups.get(2).equals(Set.of(0)));
        assertEquals(3, contiguousGroups.size());
        assertEquals(6, indexes.size());
    }

    @Test
    public void generateContiguousGroups_emptySet() {
        Set<Integer> indexes = new HashSet<>();

        List<Set<Integer>> contiguousGroups = GridUtility.generateContiguousGroupsSortedBySize(indexes);
        assertEquals(0, contiguousGroups.size());
    }

    @Test
    public void generateContiguousGroups_oneGroup() {
        Set<Integer> indexes = Set.of(6,9,10);

        List<Set<Integer>> contiguousGroups = GridUtility.generateContiguousGroupsSortedBySize(indexes);

        assertTrue(contiguousGroups.get(0).equals(Set.of(6,9,10)));
        assertEquals(1, contiguousGroups.size());

    }
}
