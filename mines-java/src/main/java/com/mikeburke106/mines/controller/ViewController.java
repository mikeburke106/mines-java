package com.mikeburke106.mines.controller;/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.basic.view.BasicMinesView;
import com.mikeburke106.mines.model.GameControlStrategy;

/**
 * Created by Mike Burke on 4/8/17.
 */
public interface ViewController {
    void setGameController(GameControlStrategy gameController);
    void setViewListener(BasicMinesView.Listener listener);
}
