package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.api.model.*;
import com.mikeburke106.mines.basic.controller.BasicGameController;
import com.mikeburke106.mines.basic.controller.BasicViewController;
import com.mikeburke106.mines.basic.model.*;
import com.mikeburke106.mines.basic.view.BasicMinesView;

import java.util.concurrent.ThreadFactory;

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
    private static final int FIELD_WIDTH = 8;
    private static final int FIELD_HEIGHT = 16;
    private static final int FIELD_NUM_MINES = 50;

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
        System.out.println(field.toString());

        Game.TimingStrategy.Factory timingStrategyFactory = new Game.TimingStrategy.Factory() {
            @Override
            public Game.TimingStrategy newInstance(long startTime) {
                return new IncrementingSecondsTimingStrategy(5000, startTime);
            }
        };

        GamePersistStrategy filePersistStrategy = null;


        Game.Factory gameFactory = new BasicGame.Factory(timingStrategyFactory);
        GameControlStrategy.Factory gameControllerFactory = new BasicGameController.Factory(gameFactory, positionPool, filePersistStrategy);
        BasicViewController controller = new BasicViewController(gameControllerFactory);
        BasicMinesView minesView = new BasicMinesView(generatePositionViewGrid(), width, height, controller);


    }

    private static BasicMinesView.PositionView[][] generatePositionViewGrid(int width, int height) {
        return new BasicMinesView.PositionView[0][0];
    }
}
