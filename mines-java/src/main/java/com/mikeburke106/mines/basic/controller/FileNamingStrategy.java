package com.mikeburke106.mines.basic.controller;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.model.Game;

/**
 * Strategy to save files with a generated name.
 * <p>
 * Created by Mike Burke on 4/20/17.
 */
public class FileNamingStrategy {
    private Game game;

    public FileNamingStrategy(Game game) {
        this.game = game;
    }

    /**
     * @return The generated file name
     */
    public String getFilename() {
        return String.valueOf(game.gameCreateTime()) +
                "_" +
                game.field().configuration().positionPool().width() +
                "x" +
                game.field().configuration().positionPool().height() +
                "_" +
                game.field().configuration().numMines() +
                ".mines";
    }
}
