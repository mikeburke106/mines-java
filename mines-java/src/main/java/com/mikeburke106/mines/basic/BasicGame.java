package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.model.Field;
import com.mikeburke106.mines.model.Game;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicGame implements Game {
    private final Field gameField;
    private final TimingStrategy timingStrategy;

    public BasicGame(Field gameField, TimingStrategy timingStrategy) {
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
}
