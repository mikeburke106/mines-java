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
public class BasicPositionFactoryTest {
    private static final int MOCK_X = 1842;
    private static final int MOCK_Y = 19;

    @Test
    public void testNewInstance() {
        Position newInstance = new BasicPosition.Factory().newInstance(MOCK_X, MOCK_Y);
        assertEquals(MOCK_X, newInstance.x());
        assertEquals(MOCK_Y, newInstance.y());
    }
}
