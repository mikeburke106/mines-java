package com.mikeburke106.mines.basic.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.model.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mike Burke on 5/6/17.
 */
public class BasicPositionPoolIteratorTest {
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

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testIterator() {
        Iterator<Position> iterator = basicPositionPool.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            Position nextPosition = iterator.next();
            assertEquals(positionArray[i++], nextPosition);
        }

        iterator.next(); // ArrayIndexOutOfBoundsException
    }

}
