package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.model.Field;
import com.mikeburke106.mines.model.Position;
import java.util.Set;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Implements a basic Field implementation using position sets for flags and mines.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class BasicField implements Field {
    private final Configuration configuration;
    private final Set<Position> mines;
    private final Set<Position> flags;

    public BasicField(Configuration configuration, Set<Position> mines, Set<Position> flags) {
        this.configuration = configuration;
        this.mines = mines;
        this.flags = flags;
    }

    @Override
    public Configuration configuration() {
        return this.configuration;
    }

    @Override
    public boolean clear(Position position) {
        return this.mines.contains(position);
    }

    @Override
    public boolean flag(Position position) {
        boolean isFlagged = this.flags.contains(position);

        if (isFlagged) {
            this.flags.remove(position);
        } else {
            this.flags.add(position);
        }

        return !isFlagged;
    }

    @Override
    public boolean isFlag(Position position) {
        return this.flags.contains(position);
    }

    @Override
    public boolean isMine(Position position) {
        return this.mines.contains(position);
    }
}

