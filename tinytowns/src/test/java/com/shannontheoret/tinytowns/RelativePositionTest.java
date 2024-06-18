package com.shannontheoret.tinytowns;

import org.junit.jupiter.api.Test;

public class RelativePositionTest {

    @Test
    public void getRelativePositionFromIndex_matches_getIndex() {
        for (int i = 0; i <= 15; i++) {
            RelativePosition relativePosition = RelativePosition.getRelativePositionFromIndex(i);
            assert(i == relativePosition.getIndex());
        }
    }
}
