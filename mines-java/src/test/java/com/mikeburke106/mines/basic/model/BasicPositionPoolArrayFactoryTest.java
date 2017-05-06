package com.mikeburke106.mines.basic.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.model.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mike Burke on 5/6/17.
 */
public class BasicPositionPoolArrayFactoryTest {
    private static final int MOCK_WIDTH = 3;
    private static final int MOCK_HEIGHT = 2;

    private int currX, currY;

    @Test
    public void testGenerate() {
        Position[] genArray = new BasicPositionPool.PositionArrayFactory(new BasicPosition.Factory(), MOCK_WIDTH,
                MOCK_HEIGHT).generate();

        currX = 0;
        currY = 0;
        for (Position position : genArray) {
            assertEquals(currX, position.x());
            assertEquals(currY, position.y());
            increment();
        }
    }

    private void increment() {
        currX++;
        if (endOfRow()) {
            moveToNextRow();
        }
    }

    private boolean endOfRow() {
        return currX == MOCK_WIDTH;
    }

    private void moveToNextRow() {
        currY++;
        currX = 0;
    }
}
