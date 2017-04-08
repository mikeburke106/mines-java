package com.mikeburke106.mines.basic.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikeburke106.mines.basic.BasicConfiguration;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Defines a configuration in JSON format.
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public class BasicConfigurationJson {
    @JsonProperty
    private int width;

    @JsonProperty
    private int height;

    @JsonProperty
    private int numMines;

    public BasicConfigurationJson() {
        /*
         * Do nothing.  Needed for Jackson deserialization.
         */
    }

    public BasicConfigurationJson(int width, int height, int numMines) {
        this.width = width;
        this.height = height;
        this.numMines = numMines;
    }

    public BasicConfigurationJson(BasicConfiguration configuration) {
        this(configuration.positionPool().width(), configuration.positionPool().height(), configuration.numMines());
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int numMines() {
        return numMines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicConfigurationJson that = (BasicConfigurationJson) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        return numMines == that.numMines;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + numMines;
        return result;
    }
}
