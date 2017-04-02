package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.model.Position;

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
