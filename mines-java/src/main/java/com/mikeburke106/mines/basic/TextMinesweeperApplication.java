package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.api.controller.ViewController;
import com.mikeburke106.mines.api.model.*;
import com.mikeburke106.mines.api.view.MinesView;
import com.mikeburke106.mines.basic.controller.BasicGameController;
import com.mikeburke106.mines.basic.controller.BasicViewController;
import com.mikeburke106.mines.basic.model.*;
import com.mikeburke106.mines.basic.view.BasicMinesView;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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
    private static final int FIELD_NUM_MINES = 25;

    public static void main(String args[]) throws Field.Configuration.InvalidConfigurationException, InterruptedException {
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
        Field.Configuration configuration = new BasicConfiguration(positionPool, numMines);

        Game.TimingStrategy.Factory timingStrategyFactory = new Game.TimingStrategy.Factory() {
            @Override
            public Game.TimingStrategy newInstance(long startTime) {
                return new IncrementingSecondsTimingStrategy(3, startTime);
            }
        };

        GamePersistStrategy filePersistStrategy = null;

        final long startTime = 0L;

        while (true) {
            System.out.println("\n\nstarting a new game!!\n\n");

            final Map<Position, BasicMinesView.PositionView> positionPositionViewMap = new HashMap<>(width * height);
            createPositionViews(positionPool, positionPositionViewMap);

            Field gameField = new BasicFieldFactory(positionProviderFactory).newInstance(configuration);
            System.out.println(gameField.toString());
            Game.Factory gameFactory = new BasicGame.Factory(timingStrategyFactory);
            Game game = gameFactory.newGame(gameField, startTime);
            GameControlStrategy.Factory gameControllerFactory = new BasicGameController.Factory(game, positionPool, filePersistStrategy);
            BasicViewController controller = new BasicViewController(gameControllerFactory);
            BasicMinesView minesView = new BasicMinesView(positionPositionViewMap, positionPool, startTime, controller);

            // start the game
            minesView.buttonPress(MinesView.ButtonValue.NEW_GAME);
            controller.setGameListener(minesView);
            game.startGameTimer();

//            for (Position clicked : positionPool) {
//                Thread.sleep(1500L);
//                controller.onItemClicked(clicked.x(), clicked.y());
//                if(controller.isGameOver()){
//                    break;
//                }
//            }
//
//            Thread.sleep(3000L);

            loopUserInput(controller, width, height);
        }
    }

    private static void loopUserInput(ViewController controller, int width, int height) {
        Scanner scanner = new Scanner(System.in);

        while (!controller.isGameOver()) {
            System.out.println("Make a move:");
            String input = scanner.nextLine();
            System.out.println("Got input: " + input);
            String[] inputs = input.split(" ");

            final char cmd = inputs[0].charAt(0);
            final int x = Integer.parseInt(inputs[1]);
            final int y = Integer.parseInt(inputs[2]);

            if (x < 0 || x >= width || y < 0 || y >= height || (cmd != 'c' && cmd != 'f')) {
                System.out.println("Invalid input.  Try again.");
            } else {
                if (cmd == 'c') {
                    controller.onItemClicked(x, y);
                } else if (cmd == 'f') {
                    controller.onItemLongClicked(x, y);
                }
            }
        }
    }

    private static void createPositionViews(Position.Pool positionPool, Map<Position, BasicMinesView.PositionView> positionPositionViewMap) {
        for (Position position : positionPool) {
            BasicMinesView.PositionView positionView = new BasicMinesView.PositionView();
            positionPositionViewMap.put(position, positionView);
        }
    }
}
