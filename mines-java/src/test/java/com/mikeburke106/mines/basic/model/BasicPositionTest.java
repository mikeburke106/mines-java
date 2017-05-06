package com.mikeburke106.mines.basic.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mike Burke on 5/6/17.
 */
public class BasicPositionTest {
    private static final int MOCK_X = 18;
    private static final int MOCK_Y = 29;

    private BasicPosition basicPosition;

    @Before
    public void setup() {
        basicPosition = new BasicPosition(MOCK_X, MOCK_Y);
    }

    @Test
    public void testX() {
        assertEquals(MOCK_X, basicPosition.x());
    }

    @Test
    public void testY() {
        assertEquals(MOCK_Y, basicPosition.y());
    }
}
