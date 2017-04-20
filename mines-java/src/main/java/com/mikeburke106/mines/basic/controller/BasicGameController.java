package com.mikeburke106.mines.basic.controller;

import com.mikeburke106.mines.api.model.Game;
import com.mikeburke106.mines.api.model.GameControlStrategy;
import com.mikeburke106.mines.api.model.GamePersistStrategy;
import com.mikeburke106.mines.api.model.Position;

import java.io.IOException;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Basic implementation of a view controller.
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public class BasicGameController implements GameControlStrategy {
    private Game.Factory gameFactory;
    private Game game;
    private Position.Pool positionPool;
    private GamePersistStrategy gamePersistStrategy;

    @Override
    public void clear(int x, int y) {
        game.field().clear(positionPool.atLocation(x, y));
    }

    @Override
    public void toggleFlag(int x, int y) {
        game.field().flag(positionPool.atLocation(x, y));
    }

    @Override
    public void saveGame(String filename) throws IOException {
        gamePersistStrategy.saveGame(game, filename);
    }

    @Override
    public void restoreGame(String filename) throws IOException {
        // TODO
    }
}
