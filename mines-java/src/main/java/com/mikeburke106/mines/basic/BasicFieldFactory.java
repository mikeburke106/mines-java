package java.com.mikeburke106.mines.basic;

import java.com.mikeburke106.mines.model.Field;
import java.com.mikeburke106.mines.model.Position;
import java.util.HashSet;
import java.util.Set;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Basic implementation of a FieldFactory.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class BasicFieldFactory implements Field.Factory {

    private Position.Provider.Factory positionProviderFactory;
    private Field.Configuration.ValidationStrategy configurationValidationStrategy;

    public BasicFieldFactory(Position.Provider.Factory positionProviderFactory) {
        this(positionProviderFactory, new ValidationStrategy());
    }

    public BasicFieldFactory(Position.Provider.Factory positionProviderFactory, Field.Configuration.ValidationStrategy configurationValidationStrategy) {
        this.positionProviderFactory = positionProviderFactory;
        this.configurationValidationStrategy = configurationValidationStrategy;
    }

    @Override
    public Field newInstance(Field.Configuration configuration) throws Field.Configuration.InvalidConfigurationException {
        configurationValidationStrategy.validate(configuration);

        final Position.Provider positionProvider = positionProviderFactory.newInstance(configuration.positionPool());
        final Set<Position> minePositions = new HashSet<>();
        final Set<Position> flagPositions = new HashSet<>();

        for (int i = 0; i < configuration.numMines(); i++) {
            try {
                minePositions.add(positionProvider.nextPosition());
            } catch (Position.Provider.NoPositionsAvailableException e) {
                /* should be impossible since we validated inputs, so something is clearly wrong with the point provider */
                throw new RuntimeException("Something is wrong with the input PositionProvider.", e);
            }
        }

        return new BasicField(configuration, minePositions, flagPositions);
    }

    /**
     * Basic validation strategy.  Validates that width, height and numMines are all positive numbers and that the number
     * of mines doesn't exceed the number of positions.
     */
    public static class ValidationStrategy implements Field.Configuration.ValidationStrategy {
        @Override
        public void validate(Field.Configuration configuration) throws Field.Configuration.InvalidConfigurationException {
            final int width = configuration.positionPool().width();
            final int height = configuration.positionPool().height();
            final int numMines = configuration.numMines();

            if (width <= 0) {
                fail("Width value must be greater than 0.");
            }
            if (height <= 0) {
                fail("Height value must be greater than 0.");
            }
            if (numMines <= 0) {
                fail("Number of mines value must be greater than 0.");
            }

            final int totalPositions = width * height;
            if (numMines > totalPositions) {
                fail("Too many mines for input field size.");
            }
        }

        private void fail(String msg) throws Field.Configuration.InvalidConfigurationException {
            throw new Field.Configuration.InvalidConfigurationException(msg);
        }
    }
}
