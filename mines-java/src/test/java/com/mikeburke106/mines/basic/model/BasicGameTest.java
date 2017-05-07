package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Field;
import com.mikeburke106.mines.api.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike on 4/29/2017.
 */
public class BasicGameTest {
    private static final long CREATE_TIME = 684384834L;
    private static final long TIME_PLAYED = 36000L;

    @Mock
    private Field mockField;

    @Mock
    private RegularIntervalTimingStrategy mockTimingStrategy;

    private BasicGame basicGame;

    @Before
    public void setup() {
        initMocks(this);

        basicGame = new BasicGame(CREATE_TIME, mockField, mockTimingStrategy);
    }

    @Test
    public void testField() {
        assertEquals(mockField, basicGame.field());
    }

    @Test
    public void testTimePlayed() {
        when(mockTimingStrategy.getCurrentTime()).thenReturn(TIME_PLAYED);
        assertEquals(TIME_PLAYED, basicGame.timePlayed());
    }

    @Test
    public void testStartGameTimer() {
        basicGame.startGameTimer();
        verify(mockTimingStrategy).start();
    }

    @Test
    public void testPauseGameTimer() {
        basicGame.pauseGameTimer();
        verify(mockTimingStrategy).pause();
    }

    @Test
    public void testGameCreateTime() {
        assertEquals(CREATE_TIME, basicGame.gameCreateTime());
    }

    @Test
    public void testTimingStrategy() {
        assertEquals(mockTimingStrategy, basicGame.timingStrategy());
    }

    @Test
    public void testEquals() {
        BasicGame first = new BasicGame(CREATE_TIME, mockField, mockTimingStrategy);
        BasicGame second = new BasicGame(CREATE_TIME, mockField, mockTimingStrategy);

        assertFalse(first == second);

        // NOTE: these assertions depend on RegularIntervalTimingStrategy.equals implementation because equals() method can't be mocked
        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }
}
