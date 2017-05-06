package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Field;
import com.mikeburke106.mines.api.model.Position;

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

    @Override
    public String toString() {
        return "config = " +
                "(" + positionPool.width() +
                "x" + positionPool.height() +
                "), " + numMines +
                " mines";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicConfiguration that = (BasicConfiguration) o;

        if (numMines != that.numMines) return false;
        return positionPool.equals(that.positionPool);
    }

    @Override
    public int hashCode() {
        int result = positionPool.hashCode();
        result = 31 * result + numMines;
        return result;
    }
}
