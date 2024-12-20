package com.shannontheoret.tinytowns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelativePositionTest {

    @Test
    public void getRelativePositionFromIndex_matches_getIndex() {
        for (int i = 0; i <= 15; i++) {
            RelativePosition relativePosition = RelativePosition.getRelativePositionFromIndex(i);
            assert(i == relativePosition.getIndex());
        }
    }

    @Test
    public void getIndex_outOfBoundsIndex_returnsNegativeOne() {
        RelativePosition relativePosition = new RelativePosition(4,2);
        assertEquals(-1, relativePosition.getIndex());
    }

    @Test
    public void isValidIndex_invalidIndex() {
        assert(!RelativePosition.isValidIndex(16));
    }

    @Test
    public void isValidIndex_validIndex() {
        assert(RelativePosition.isValidIndex(3));
    }
}
