package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.model.Field;
import com.mikeburke106.mines.model.Position;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Basic implementation of a field configuration.
 * <p>
 * Created by Mike Burke on 4/2/17.
 */
public class BasicConfiguration implements Field.Configuration {
    private final Position.Pool positionPool;
    private final int numMines;

    public BasicConfiguration(Position.Pool positionPool, int numMines) {
        this.positionPool = positionPool;
        this.numMines = numMines;
    }

    @Override
    public Position.Pool positionPool() {
        return positionPool;
    }

    @Override
    public int numMines() {
        return numMines;
    }
}
