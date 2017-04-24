package com.mikeburke106.mines.api.controller;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.model.GameControlStrategy;
import com.mikeburke106.mines.api.view.MinesView;

/**
 * Created by Mike Burke on 4/20/17.
 */
public interface ViewController extends MinesView.Listener {
    void setGameListener(GameControlStrategy.Listener gameListener);

    /**
     * @return Whether or not the game is over
     */
    boolean isGameOver();
}
