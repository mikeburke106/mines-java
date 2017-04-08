package com.mikeburke106.mines.model;

import java.util.List;

/**
 * Definition of a Game.
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public interface Game {
    /**
     * @return The game field
     */
    Field field();

    /**
     * @return The amount of time played
     */
    long timePlayed();

    /**
     * Starts the game timer.
     */
    void startGameTimer();

    /**
     * Pauses the game timer.
     */
    void pauseGameTimer();

    /**
     * Strategy for tracking game time.
     */
    interface TimingStrategy {
        /**
         * Starts tracking game time.
         */
        void start();

        /**
         * Stops tracking game time.
         */
        void pause();

        /**
         * @return The current game time
         */
        long getCurrentTime();
    }

    /**
     * Factory for Game objects.
     */
    interface Factory {
        /**
         * Creates a new game with given inputs
         *
         * @param gameField Field for the game
         * @param startTime Start time of the game
         * @return
         */
        Game newGame(Field gameField, long startTime);
    }
}
