package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.model.Position;

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
                currY++;
                currX = 0;
            }
        }

        private boolean endOfRow() {
            return currX == positionPool.width();
        }

        @Override
        public void remove() {
            throw new RuntimeException();
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
