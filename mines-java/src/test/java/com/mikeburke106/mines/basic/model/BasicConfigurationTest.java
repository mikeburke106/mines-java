package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Field;
import com.mikeburke106.mines.api.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicConfigurationTest {
    private static final int NUM_MINES = 1240;

    @Mock
    private Position.Pool mockPositionPool;

    private BasicConfiguration basicConfiguration;

    @Before
    public void setup() {
        initMocks(this);
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
}
