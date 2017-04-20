package com.mikeburke106.mines.basic.controller;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.controller.ViewController;
import com.mikeburke106.mines.api.model.GameControlStrategy;
import com.mikeburke106.mines.api.view.MinesView;

import java.io.IOException;

/**
 * Created by Mike Burke on 4/19/17.
 */
public class BasicViewController implements ViewController {
    private GameControlStrategy.Factory gameControlStrategyFactory;
    private GameControlStrategy gameControlStrategy;
    private FileNamingStrategy fileNamingStrategy;

    public BasicViewController(GameControlStrategy.Factory gameControlStrategyFactory) {
        this.gameControlStrategyFactory = gameControlStrategyFactory;
    }

    @Override
    public void onItemClicked(int x, int y) {
        this.gameControlStrategy.clear(x, y);
    }

    @Override
    public void onItemLongClicked(int x, int y) {
        this.gameControlStrategy.toggleFlag(x, y);
    }

    @Override
    public void onButtonClicked(MinesView.ButtonValue buttonValue) {
        switch (buttonValue) {
            case NEW_GAME:
                gameControlStrategy = gameControlStrategyFactory.newInstance();
                break;
            case SAVE_GAME:
                saveGame();
                break;
            case RESTORE_GAME:
                restoreGame();
                break;
            case RESTART_GAME:
                restartGame();
                break;
            case END_GAME:
                endGame();
                break;
        }
    }

    private void saveGame() {
        String filename = fileNamingStrategy.getFilename();
        try {
            gameControlStrategy.saveGame(filename);
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }

    private void restoreGame() {
        String filename = fileNamingStrategy.getFilename();
        try {
            gameControlStrategy.restoreGame(filename);
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }

    private void restartGame() {
        // TODO
    }

    private void endGame() {
        // TODO
    }
}
