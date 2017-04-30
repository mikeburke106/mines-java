package com.mikeburke106.mines.api.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

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
     * @return The time at which the game was originally created
     */
    long gameCreateTime();

    /**
     * @return The timing strategy
     */
    TimingStrategy timingStrategy();

    /**
     * Strategy for tracking game time.
     */
    interface TimingStrategy {
        /**
         * Factory for creating new timing strategy instances.
         */
        interface Factory {
            /**
             * Creates a new timing strategy instance.
             *
             * @param startTime The starting time for the timing strategy
             * @return The new timing strategy instance
             */
            TimingStrategy newInstance(long startTime);
        }

        /**
         * Listener for timing updates.
         */
        interface Listener {
            /**
             * Notifies listener that time has incremented.
             *
             * @param newTime The new time
             */
            void timeUpdate(long newTime);

            /**
             * Default timing strategy listener (callbacks do nothing).
             */
            Listener DEFAULT = new Listener() {
                @Override
                public void timeUpdate(long newTime) {
                  /* do nothing */
                }
            };
        }

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

        /**
         * Sets a listener on the timing strategy.
         *
         * @param listener The listener to set
         */
        void setListener(Listener listener);
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
         * @return The new game instance
         */
        Game newGame(Field gameField, long startTime);
    }
}
