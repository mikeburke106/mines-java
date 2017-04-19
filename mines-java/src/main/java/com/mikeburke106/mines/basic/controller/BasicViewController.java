package com.mikeburke106.mines.basic.controller;/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.basic.view.BasicMinesView;
import com.mikeburke106.mines.api.controller.ViewController;
import com.mikeburke106.mines.api.model.GameControlStrategy;

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicViewController implements ViewController {
    private GameControlStrategy gameController;

    @Override
    public void setGameController(GameControlStrategy gameController) {
        this.gameController = gameController;
    }

    @Override
    public void setViewListener(BasicMinesView.Listener listener) {

    }
}
