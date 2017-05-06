package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Position;

import java.util.Arrays;
import java.util.Iterator;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Basic implementation of a position pool.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class BasicPositionPool implements Position.Pool {
    private final Position[] pool;
    private final int width;
    private final int height;

    public BasicPositionPool(Position.Factory positionFactory, int width, int height) {
        this(new PositionArrayFactory(positionFactory, width, height).generate(), width, height);
    }

    public BasicPositionPool(Position[] pool, int width, int height) {
        if (pool.length != (width * height)) {
            throw new IllegalArgumentException("Pool size does not match given width and height parameters.");
        }

        this.pool = pool;
        this.width = width;
        this.height = height;
    }

    @Override
    public Position atLocation(int x, int y) {
        return pool[indexForCoordinate(width, x, y)];
    }

    @Override
    public int size() {
        return pool.length;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    private static int indexForCoordinate(int width, int x, int y) {
        return (width * y) + x;
    }

    @Override
    public Iterator<Position> iterator() {
        return new BasicPositionPoolIterator(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicPositionPool positions = (BasicPositionPool) o;

        if (width != positions.width) return false;
        if (height != positions.height) return false;

        final int size = pool.length;
        for(int i=0; i<size; i++){
            Position thisPosition = pool[i];
            Position thatPosition = positions.pool[i];
            if(thisPosition.getClass() != thatPosition.getClass()) return false;
            if(!thisPosition.equals(thatPosition)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(pool);
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    static class BasicPositionPoolIterator implements Iterator<Position> {

        private Position.Pool positionPool;
        private int currX, currY;

        public BasicPositionPoolIterator(Position.Pool positionPool) {
            this.positionPool = positionPool;
            this.currX = 0;
            this.currY = 0;
        }

        @Override
        public boolean hasNext() {
            return currY < positionPool.height();
        }

        @Override
        public Position next() {
            Position next = positionPool.atLocation(currX, currY);
            increment();
            return next;
        }

        private void increment() {
            currX++;
            if (endOfRow()) {
                moveToNextRow();
            }
        }

        private boolean endOfRow() {
            return currX == positionPool.width();
        }

        private void moveToNextRow() {
            currY++;
            currX = 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    static class PositionArrayFactory {
        Position.Factory positionFactory;
        int width;
        int height;

        PositionArrayFactory(Position.Factory positionFactory, int width, int height) {
            this.positionFactory = positionFactory;
            this.width = width;
            this.height = height;
        }

        Position[] generate() {
            Position[] result = new Position[width * height];

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    setPosition(result, x, y);
                }
            }

            return result;
        }

        private void setPosition(Position[] pool, int x, int y) {
            pool[indexForCoordinate(width, x, y)] = this.positionFactory.newInstance(x, y);
        }
    }
}
