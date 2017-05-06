package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Field;
import com.mikeburke106.mines.api.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicConfigurationTest {
    private static final int MOCK_WIDTH = 2345;
    private static final int MOCK_HEIGHT = 768;
    private static final int NUM_MINES = 1240;

    @Mock
    private Position.Pool mockPositionPool;

    private BasicConfiguration basicConfiguration;

    @Before
    public void setup() {
        initMocks(this);

        when(mockPositionPool.width()).thenReturn(MOCK_WIDTH);
        when(mockPositionPool.height()).thenReturn(MOCK_HEIGHT);

        basicConfiguration = new BasicConfiguration(mockPositionPool, NUM_MINES);
    }

    @Test
    public void testAdheresToInterface() {
        assertTrue(basicConfiguration instanceof Field.Configuration);
    }

    @Test
    public void testPositionPool() {
        assertEquals(mockPositionPool, basicConfiguration.positionPool());
    }

    @Test
    public void testNumMines() {
        assertEquals(NUM_MINES, basicConfiguration.numMines());
    }

    @Test
    public void testToString() {
        assertEquals("config = (" + MOCK_WIDTH + "x" + MOCK_HEIGHT + "), " + NUM_MINES + " mines",
                basicConfiguration.toString());
    }

    @Test
    public void testEquals() {
        BasicConfiguration first = new BasicConfiguration(new BasicPositionPool(new BasicPosition.Factory(), MOCK_WIDTH, MOCK_HEIGHT),
                NUM_MINES);
        BasicConfiguration second = new BasicConfiguration(new BasicPositionPool(new BasicPosition.Factory(), MOCK_WIDTH, MOCK_HEIGHT),
                NUM_MINES);

        assertFalse(first == second);

        // NOTE: these assertions depend on BasicPositionPool.equals implementation because equals() method can't be mocked
        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }
}
