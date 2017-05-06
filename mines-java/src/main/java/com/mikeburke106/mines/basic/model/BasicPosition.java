package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Position;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * A basic implementation of a position.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class BasicPosition implements Position {

    private final int x;
    private final int y;

    public BasicPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int x() {
        return this.x;
    }

    @Override
    public int y() {
        return this.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicPosition that = (BasicPosition) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    /**
     * A basic implementation of a position factory for BasicPosition instances.
     */
    public static class Factory implements Position.Factory {
        @Override
        public Position newInstance(final int x, final int y) {
            return new BasicPosition(x, y);
        }
    }
}
