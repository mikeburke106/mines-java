package com.mikeburke106.mines.basic.controller;

import com.mikeburke106.mines.api.model.Game;
import com.mikeburke106.mines.api.model.GameControlStrategy;
import com.mikeburke106.mines.api.model.GamePersistStrategy;
import com.mikeburke106.mines.api.model.Position;
import com.mikeburke106.mines.basic.model.BasicPosition;

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
    private boolean gameOver;
    private Game game;
    private Position.Pool positionPool;
    private GamePersistStrategy gamePersistStrategy;
    private Listener listener;

    public BasicGameController(Game game, Position.Pool positionPool, GamePersistStrategy gamePersistStrategy) {
        this.game = game;
        this.positionPool = positionPool;
        this.gamePersistStrategy = gamePersistStrategy;
    }

    @Override
    public void clear(int x, int y) {
        Position position = positionPool.atLocation(x, y);
        boolean isMine = game.field().clear(position);
        if (isMine) {
            listener.positionExploded(x, y);
            listener.gameLost();
            gameOver = true;
        } else {
            listener.positionCleared(x, y, calculateAdjacent(position));
        }
    }

    // TODO: this strategy should be injected
    private int calculateAdjacent(Position position) {
        int adjacent = 0;

        Position[] positionDiffMatrix = new Position[]{
                new BasicPosition(-1, -1),
                new BasicPosition(0, -1),
                new BasicPosition(1, -1),
                new BasicPosition(-1, 0),
                new BasicPosition(1, 0),
                new BasicPosition(-1, 1),
                new BasicPosition(0, 1),
                new BasicPosition(1, 1),
        };

        for (Position diffPosition : positionDiffMatrix) {
            final int dx = diffPosition.x();
            final int dy = diffPosition.y();
            final int checkX = position.x() + dx;
            final int checkY = position.y() + dy;

            if (diffPositionExists(checkX, checkY)) {
                Position positionToCheck = positionPool.atLocation(checkX, checkY);
                if (game.field().isMine(positionToCheck)) {
                    adjacent++;
                }

            }
        }

        return adjacent;
    }

    private boolean diffPositionExists(int x, int y) {
        return x >= 0 && x < positionPool.width() &&
                y >= 0 && y < positionPool.height();
    }

    @Override
    public void toggleFlag(int x, int y) {
        boolean isFlagged = game.field().flag(positionPool.atLocation(x, y));
        if (isFlagged) {
            listener.positionFlagged(x, y);
        } else {
            listener.positionUnflagged(x, y);
        }
    }

    @Override
    public void saveGame(String filename) throws IOException {
        gamePersistStrategy.saveGame(game, filename);
    }

    @Override
    public void restoreGame(String filename) throws IOException {
        // TODO
    }

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
        game.timingStrategy().setListener(listener);
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    public static class Factory implements GameControlStrategy.Factory {
        private Game game;
        private Position.Pool positionPool;
        private GamePersistStrategy gamePersistStrategy;

        public Factory(Game game, Position.Pool positionPool, GamePersistStrategy gamePersistStrategy) {
            this.game = game;
            this.positionPool = positionPool;
            this.gamePersistStrategy = gamePersistStrategy;
        }

        @Override
        public GameControlStrategy newInstance() {
            return new BasicGameController(game, positionPool, gamePersistStrategy);
        }

        @Override
        public GameControlStrategy newInstance(GameControlStrategy copy) {
            // TODO
            throw new UnsupportedOperationException();
        }
    }
}
