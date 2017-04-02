package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.model.Field;
import com.mikeburke106.mines.model.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A basic, terminal-based minesweeper application.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class TextMinesweeperApplication {
    private static final int FIELD_WIDTH = 10;
    private static final int FIELD_HEIGHT = 5;
    private static final int FIELD_NUM_MINES = 15;

    public static void main(String args[]) throws Field.Configuration.InvalidConfigurationException, Position.Provider.NoPositionsAvailableException, InterruptedException {
        final Position.Pool positionPool = new BasicPositionPool(new BasicPosition.Factory(), FIELD_WIDTH, FIELD_HEIGHT);
        Position.Provider.Factory positionProviderFactory = new RandomPositionProvider.Factory();
        Field.Factory fieldfactory = new BasicFieldFactory(positionProviderFactory);
        Field.Configuration configuration = new BasicConfiguration(positionPool, FIELD_NUM_MINES);

        // continuously generate new fields with the given information
        while(true) {
            System.out.println("\n\nNew field:\n");
            Field field = fieldfactory.newInstance(configuration);
            new PrintStreamFieldPrintStrategy(System.out).printField(field);
            Thread.sleep(2000);
        }
    }
}
