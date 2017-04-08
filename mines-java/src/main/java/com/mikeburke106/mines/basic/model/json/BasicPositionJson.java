package com.mikeburke106.mines.basic.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikeburke106.mines.basic.model.BasicPosition;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Defines a position in JSON format.
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public class BasicPositionJson {
    @JsonProperty
    private int x;

    @JsonProperty
    private int y;

    public BasicPositionJson() {
        /*
         * Do nothing.  Needed for Jackson deserialization.
         */
    }

    public BasicPositionJson(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BasicPositionJson(BasicPosition basicPosition) {
        this(basicPosition.x(), basicPosition.y());
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicPositionJson that = (BasicPositionJson) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
