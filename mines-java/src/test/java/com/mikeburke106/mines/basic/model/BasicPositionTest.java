package com.mikeburke106.mines.basic.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testEquals() {
        BasicPosition first = new BasicPosition(MOCK_X, MOCK_Y);
        BasicPosition second = new BasicPosition(MOCK_X, MOCK_Y);

        assertFalse(first == second);

        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }
}
