package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Position;

import java.util.List;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * A particular position provider that will only return each available position once.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public abstract class UniquePositionProvider implements Position.Provider {
    protected final List<Position> availablePositions;

    public UniquePositionProvider(List<Position> availablePositions) {
        this.availablePositions = availablePositions;
    }

    /**
     * Takes the position at the specified index.
     *
     * @param index The index to take
     * @return The removed Position.
     */
    protected Position take(int index) {
        return availablePositions.remove(index);
    }

    @Override
    public String toString() {
        return "UniquePositionProvider{" +
                "availablePositions=" + availablePositions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UniquePositionProvider that = (UniquePositionProvider) o;

        return availablePositions.equals(that.availablePositions);
    }

    @Override
    public int hashCode() {
        return availablePositions.hashCode();
    }
}
