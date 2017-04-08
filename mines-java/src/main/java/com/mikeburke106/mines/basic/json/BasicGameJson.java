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
    private long timePlayed;

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

    public BasicGameJson(long timePlayed, BasicConfigurationJson configuration, List<BasicPositionJson> mines,
                         List<BasicPositionJson> flags, List<BasicPositionJson> cleared) {
        this.timePlayed = timePlayed;
        this.configuration = configuration;
        this.mines = mines;
        this.flags = flags;
        this.cleared = cleared;
    }

    public long timePlayed() {
        return timePlayed;
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
