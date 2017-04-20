package com.mikeburke106.mines.api.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import java.io.IOException;

/**
 * Interface definition of the game controller.  This is the main API for triggering game events from the view controller.
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public interface GameControlStrategy {

    /**
     * Interface definition for a listener of the game controller.
     */
    interface Listener {
        /**
         * Callback for when the associated position has been cleared successfully.
         *
         * @param x        The position's x-coordinate
         * @param y        The position's y-coordinate
         * @param adjacent The number of mines adjacent to the cleared position
         */
        void positionCleared(int x, int y, int adjacent);

        /**
         * Callback for when the associated position was cleared to find a mine.  BOOM!!
         *
         * @param x The position's x-coordinate
         * @param y The position's y-coordinate
         */
        void positionExploded(int x, int y);

        /**
         * Callback for when the associated position was flagged.
         *
         * @param x The position's x-coordinate
         * @param y The position's y-coordinate
         */
        void positionFlagged(int x, int y);

        /**
         * Callback for when the associated position was unflagged.
         *
         * @param x The position's x-coordinate
         * @param y The position's y-coordinate
         */
        void positionUnflagged(int x, int y);

        /**
         * Callback for game time updates.
         *
         * @param time The updated game time
         */
        void timeUpdate(long time);

        /**
         * Callback for when the game has been completed successfully.
         */
        void gameWon();

        /**
         * Callback for when the game has been lost.  This callback should happen after a positionExploded event.
         */
        void gameLost();
    }

    /**
     * Factory pattern for generating new GameControlStrategy objects.
     */
    interface Factory {
        /**
         * Generates a new instance of GameControlStrategy.
         *
         * @return The new instance
         */
        GameControlStrategy newInstance();

        /**
         * Creates a new copy of the input strategy (for a new game).
         *
         * @param copy The object to copy
         * @return The new instance of the input strategy
         */
        GameControlStrategy newInstance(GameControlStrategy copy);
    }

    /**
     * Indicates the game should mark the input coordinate as cleared.  This call will trigger a position update on the
     * listener - positionCleared if the position did not contain a mine, positionExploded if it did.
     *
     * @param x The x-coordinate of the position
     * @param y The y-coordinate of the position
     */
    void clear(int x, int y);

    /**
     * Indicates the game should toggle the flag status on the input coordinate.  This call will trigger a position
     * update on the listener - positionFlagged if the position was flagged, positionUnflagged if it was unflagged.
     *
     * @param x
     * @param y
     */
    void toggleFlag(int x, int y);
    /**
     * Saves the game to persistent storage.
     *
     * @param filename A name to track the saved file
     * @throws IOException When some kind of IO operation fails
     */
    void saveGame(String filename) throws IOException;

    /**
     * Restores the game from persistent storage.
     *
     * @param filename A name to retrieve the saved file
     * @throws IOException When some kind of IO operation fails
     */
    void restoreGame(String filename) throws IOException;

}
