package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.model.Field;
import com.mikeburke106.mines.model.Position;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * A basic, terminal-based minesweeper application.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class TextMinesweeperApplication {
    private static final int FIELD_WIDTH = 10;
    private static final int FIELD_HEIGHT = 10;
    private static final int FIELD_NUM_MINES = 1;

    public static void main(String args[]) throws Field.Configuration.InvalidConfigurationException {
        int width = FIELD_WIDTH;
        int height = FIELD_HEIGHT;
        int numMines = FIELD_NUM_MINES;

        if (args.length == 3) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
            numMines = Integer.parseInt(args[2]);
        }

        final Position.Pool positionPool = new BasicPositionPool(new BasicPosition.Factory(), width, height);
        Position.Provider.Factory positionProviderFactory = new RandomPositionProvider.Factory();
        Field.Factory fieldfactory = new BasicFieldFactory(positionProviderFactory);
        Field.Configuration configuration = new BasicConfiguration(positionPool, numMines);

        System.out.println("\n\nNew field: (" + width + "x" + height + "), " + numMines + " mines\n");
        Field field = fieldfactory.newInstance(configuration);
        new PrintStreamFieldPrintStrategy(System.out).printField(field);
    }
}
