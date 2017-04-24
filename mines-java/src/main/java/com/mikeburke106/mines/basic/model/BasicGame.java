package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Field;
import com.mikeburke106.mines.api.model.Game;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Basic implementation of a Game.
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public class BasicGame implements Game {
    private final long gameCreateTime;
    private final Field gameField;
    private final TimingStrategy timingStrategy;

    public BasicGame(long gameCreateTime, Field gameField, TimingStrategy timingStrategy) {
        this.gameCreateTime = gameCreateTime;
        this.gameField = gameField;
        this.timingStrategy = timingStrategy;
    }

    @Override
    public Field field() {
        return gameField;
    }

    @Override
    public long timePlayed() {
        return timingStrategy.getCurrentTime();
    }

    @Override
    public void startGameTimer() {
        timingStrategy.start();
    }

    @Override
    public void pauseGameTimer() {
        timingStrategy.pause();
    }

    @Override
    public long gameCreateTime() {
        return gameCreateTime;
    }

    @Override
    public TimingStrategy timingStrategy() {
        return this.timingStrategy;
    }

    public static class Factory implements Game.Factory {

        private TimingStrategy.Factory timingStrategyFactory;

        public Factory(TimingStrategy.Factory timingStrategyFactory) {
            this.timingStrategyFactory = timingStrategyFactory;
        }

        @Override
        public Game newGame(Field gameField, long startTime) {
            return new BasicGame(System.currentTimeMillis(), gameField, timingStrategyFactory.newInstance(startTime));
        }
    }
}
