package com.mikeburke106.mines.basic.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Defines a game in JSON format.
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public class BasicGameJson {
    @JsonProperty
    private long gameCreateTime;

    @JsonProperty
    private long elapsedTime;

    @JsonProperty
    private BasicConfigurationJson configuration;

    @JsonProperty
    private List<BasicPositionJson> mines;

    @JsonProperty
    private List<BasicPositionJson> flags;

    @JsonProperty
    private List<BasicPositionJson> cleared;

    public BasicGameJson() {
        /*
         * Do nothing.  Needed for Jackson deserialization.
         */
    }

    public BasicGameJson(long gameCreateTime, long elapsedTime, BasicConfigurationJson configuration,
                         List<BasicPositionJson> mines, List<BasicPositionJson> flags, List<BasicPositionJson> cleared) {
        this.gameCreateTime = gameCreateTime;
        this.elapsedTime = elapsedTime;
        this.configuration = configuration;
        this.mines = mines;
        this.flags = flags;
        this.cleared = cleared;
    }

    public long gameCreateTime(){
        return gameCreateTime;
    }

    public long elapsedTime() {
        return elapsedTime;
    }

    public BasicConfigurationJson configuration() {
        return configuration;
    }

    public List<BasicPositionJson> mines() {
        return mines;
    }

    public List<BasicPositionJson> flags() {
        return flags;
    }

    public List<BasicPositionJson> cleared() {
        return cleared;
    }
}
