package com.mikeburke106.mines.basic.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mike Burke on 5/6/17.
 */
public class BasicPositionPoolTest {
    private static final int MOCK_WIDTH = 3;
    private static final int MOCK_HEIGHT = 2;

    private Position[] positionArray;

    private BasicPositionPool basicPositionPool;

    @Before
    public void setup() {
        positionArray = new BasicPosition[MOCK_HEIGHT * MOCK_WIDTH];
        positionArray[0] = new BasicPosition(0, 0);
        positionArray[1] = new BasicPosition(1, 0);
        positionArray[2] = new BasicPosition(2, 0);
        positionArray[3] = new BasicPosition(0, 1);
        positionArray[4] = new BasicPosition(1, 1);
        positionArray[5] = new BasicPosition(2, 1);

        basicPositionPool = new BasicPositionPool(positionArray, MOCK_WIDTH, MOCK_HEIGHT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalPoolSize() {
        new BasicPositionPool(new Position[0], 0, 1); // IllegalArgumentException
    }

    @Test
    public void testAtLocation0() {
        assertEquals(positionArray[0], basicPositionPool.atLocation(0, 0));
    }

    @Test
    public void testAtLocation1() {
        assertEquals(positionArray[1], basicPositionPool.atLocation(1, 0));
    }

    @Test
    public void testAtLocation2() {
        assertEquals(positionArray[2], basicPositionPool.atLocation(2, 0));
    }

    @Test
    public void testAtLocation3() {
        assertEquals(positionArray[3], basicPositionPool.atLocation(0, 1));
    }

    @Test
    public void testAtLocation4() {
        assertEquals(positionArray[4], basicPositionPool.atLocation(1, 1));
    }

    @Test
    public void testAtLocation5() {
        assertEquals(positionArray[5], basicPositionPool.atLocation(2, 1));
    }

    @Test
    public void testSize() {
        assertEquals(positionArray.length, basicPositionPool.size());
    }

    @Test
    public void testWidth() {
        assertEquals(MOCK_WIDTH, basicPositionPool.width());
    }

    @Test
    public void testHeight() {
        assertEquals(MOCK_HEIGHT, basicPositionPool.height());
    }

}
