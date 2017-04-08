package com.mikeburke106.mines.basic.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikeburke106.mines.basic.BasicConfiguration;

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
}
