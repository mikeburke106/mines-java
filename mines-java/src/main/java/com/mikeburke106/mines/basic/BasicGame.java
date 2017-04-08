package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.model.Field;
import com.mikeburke106.mines.model.Game;

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
