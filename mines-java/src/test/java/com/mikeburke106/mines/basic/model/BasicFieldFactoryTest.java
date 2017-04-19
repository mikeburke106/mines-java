package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Field;
import com.mikeburke106.mines.api.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Mike on 4/18/2017.
 */
public class BasicFieldFactoryTest {
    private static final int MOCK_WIDTH = 4;
    private static final int MOCK_HEIGHT = 1;

    private static class MockPositionProviderFactory implements Position.Provider.Factory {
        private List<Position> positions;
        private int index;

        public MockPositionProviderFactory(List<Position> positions) {
            this.positions = positions;
            this.index = 0;
        }

        @Override
        public Position.Provider newInstance(Position.Pool positionPool) {
            return new Position.Provider() {
                @Override
                public Position nextPosition() throws NoPositionsAvailableException {
                    if (index >= positions.size()) {
                        throw new NoPositionsAvailableException();
                    }

                    return positions.get(index++);
                }
            };
        }
    }

    private static class ThrowExceptionProviderFactory implements Position.Provider.Factory {
        @Override
        public Position.Provider newInstance(Position.Pool positionPool) {
            return new Position.Provider() {
                @Override
                public Position nextPosition() throws NoPositionsAvailableException {
                    throw new NoPositionsAvailableException();
                }
            };
        }
    }

    @Mock
    private Position mockPosition0, mockPosition1, mockPosition2, mockPosition3;

    private List<Position> positions;

    @Before
    public void setup() {
        initMocks(this);
        positions = new ArrayList<>(4);
        positions.add(mockPosition0);
        positions.add(mockPosition1);
        positions.add(mockPosition2);
        positions.add(mockPosition3);

    }

    @Test
    public void testEmptyField() throws Field.Configuration.InvalidConfigurationException {
        BasicFieldFactory basicFieldFactory = new BasicFieldFactory(new MockPositionProviderFactory(positions),
                new Field.Configuration.ValidationStrategy() {
                    @Override
                    public void validate(Field.Configuration configuration) throws Field.Configuration.InvalidConfigurationException {
                        // don't validate anything
                    }
                });
        final int numMines = 0;
        Position.Pool positionPool = new BasicPositionPool(
                new Position[]{mockPosition0, mockPosition1, mockPosition2, mockPosition3},
                MOCK_WIDTH, MOCK_HEIGHT);
        Field.Configuration config = new BasicConfiguration(positionPool, numMines);

        Field generatedField = basicFieldFactory.newInstance(config);

        assertTrue(generatedField instanceof Field);

        for (Position position : positionPool) {
            assertFalse(generatedField.isMine(position));
            assertFalse(generatedField.isFlag(position));
        }
    }

    @Test
    public void testFirstTwoMinesField() throws Field.Configuration.InvalidConfigurationException {
        final int numMines = 2;
        Position.Pool positionPool = new BasicPositionPool(
                new Position[]{mockPosition0, mockPosition1, mockPosition2, mockPosition3},
                MOCK_WIDTH, MOCK_HEIGHT);
        Field.Configuration config = new BasicConfiguration(positionPool, numMines);
        BasicFieldFactory basicFieldFactory = new BasicFieldFactory(new MockPositionProviderFactory(positions));

        Field generatedField = basicFieldFactory.newInstance(config);

        for(int i=0; i<=1; i++){
            assertTrue(generatedField.isMine(positionPool.atLocation(i, 0)));
        }

        for(int i=2; i<=3; i++){
            assertFalse(generatedField.isMine(positionPool.atLocation(i, 0)));
        }
    }

    @Test(expected = RuntimeException.class)
    public void testMoreMinesThanPositions() throws Field.Configuration.InvalidConfigurationException {
        final int numMines = 1;
        Position.Pool positionPool = new BasicPositionPool(
                new Position[]{mockPosition0, mockPosition1, mockPosition2, mockPosition3},
                MOCK_WIDTH, MOCK_HEIGHT);
        Field.Configuration config = new BasicConfiguration(positionPool, numMines);
        BasicFieldFactory basicFieldFactory = new BasicFieldFactory(new ThrowExceptionProviderFactory(),
                new Field.Configuration.ValidationStrategy() {
                    @Override
                    public void validate(Field.Configuration configuration) throws Field.Configuration.InvalidConfigurationException {
                        // don't validate anything
                    }
                });
        basicFieldFactory.newInstance(config);
    }
}
