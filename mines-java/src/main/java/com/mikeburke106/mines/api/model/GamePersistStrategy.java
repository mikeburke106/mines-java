package com.mikeburke106.mines.api.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import java.io.IOException;

/**
 * Strategy-pattern style design for saving and restoring a game to and from some kind of persistent storage.
 *
 * Created by Mike Burke on 4/20/17.
 */
public interface GamePersistStrategy {
    /**
     * Saves the game to persistent storage.
     *
     * @param game The game to save
     * @param filename A name to track the saved file
     * @throws IOException When some kind of IO operation fails
     */
    void saveGame(Game game, String filename) throws IOException;

    /**
     * Restores the game from persistent storage.
     *
     * @param filename A name to retrieve the saved file
     * @return The restored game
     * @throws IOException When some kind of IO operation fails
     */
    Game restoreGame(String filename) throws IOException;
}
