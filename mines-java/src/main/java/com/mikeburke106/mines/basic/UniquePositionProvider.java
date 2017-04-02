package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.model.Position;
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
}
