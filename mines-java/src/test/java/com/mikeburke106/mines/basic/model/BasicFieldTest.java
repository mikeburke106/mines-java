package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Mike on 4/18/2017.
 */
public class BasicFieldTest {
    // [ ][x][ ]
    // [x][xP][P]
    private static final int MOCK_WIDTH = 3;
    private static final int MOCK_HEIGHT = 2;

    @Mock
    private BasicPosition mockPosition0, mockPosition1, mockPosition2,
            mockPosition3, mockPosition4, mockPosition5;

    @Mock
    private BasicConfiguration mockConfiguration;

    private Position.Pool mockPositionPool;

    private BasicField basicField;

    @Before
    public void setup() {
        initMocks(this);

        Set<Position> mockMineSet = new HashSet<>(3);
        mockMineSet.add(mockPosition1);
        mockMineSet.add(mockPosition3);
        mockMineSet.add(mockPosition4);

        Set<Position> mockFlagSet = new HashSet<>(3);
        mockFlagSet.add(mockPosition4);
        mockFlagSet.add(mockPosition5);

        mockPositionPool = new BasicPositionPool(new Position[]{mockPosition0, mockPosition1, mockPosition2,
                mockPosition3, mockPosition4, mockPosition5}, MOCK_WIDTH, MOCK_HEIGHT);

        when(mockConfiguration.positionPool()).thenReturn(mockPositionPool);

        basicField = new BasicField(mockConfiguration, mockMineSet, mockFlagSet);
    }

    @Test
    public void testConfiguration() {
        assertEquals(basicField.configuration(), mockConfiguration);
    }

    @Test
    public void testClearEmptyPosition() {
        assertFalse(basicField.clear(mockPosition0));
    }

    @Test
    public void testClearMinePosition() {
        assertTrue(basicField.clear(mockPosition1));
    }

    @Test
    public void testClearFlagPosition() {
        assertFalse(basicField.flag(mockPosition5));
    }

    @Test
    public void testSetFlagPosition() {
        assertTrue(basicField.flag(mockPosition2));
    }

    @Test
    public void testIsFlagTrue() {
        assertTrue(basicField.isFlag(mockPosition4));
    }

    @Test
    public void testIsFlagFalse() {
        assertFalse(basicField.isFlag(mockPosition3));
    }

    @Test
    public void testIsMineTrue() {
        assertTrue(basicField.isMine(mockPosition3));
    }

    @Test
    public void testIsMineFalse() {
        assertFalse(basicField.isMine(mockPosition0));
    }

    @Test
    public void testToString() {
        assertEquals("[ ][x][ ]\n[x][x][ ]\n", basicField.toString());
    }
}
