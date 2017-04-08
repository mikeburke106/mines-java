package com.mikeburke106.mines.basic.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicGameJsonDeserializeTest {
    private static final long GAME_START_TIME = 1234567890L;
    private static final long ELAPSED_TIME = 17L;
    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;
    private static final int NUM_MINES = 6;
    private static final String EXPECTED_JSON =
            "{" +
                    "\"gameCreateTime\":1234567890," +
                    "\"elapsedTime\":17," +
                    "\"configuration\":{\"width\":4,\"height\":4,\"numMines\":6}," +
                    "\"mines\":[{\"x\":0,\"y\":1},{\"x\":0,\"y\":2},{\"x\":1,\"y\":2},{\"x\":1,\"y\":3},{\"x\":2,\"y\":0},{\"x\":2,\"y\":3}]," +
                    "\"flags\":[{\"x\":1,\"y\":2},{\"x\":2,\"y\":0},{\"x\":3,\"y\":1}]," +
                    "\"cleared\":[{\"x\":0,\"y\":0},{\"x\":0,\"y\":3},{\"x\":1,\"y\":0},{\"x\":1,\"y\":1},{\"x\":2,\"y\":2},{\"x\":3,\"y\":0}]" +
                    "}";

    private List<BasicPositionJson> minesJson;
    private List<BasicPositionJson> flagsJson;
    private List<BasicPositionJson> clearedJson;

    @Before
    public void setup(){
        minesJson = Arrays.asList(
                new BasicPositionJson(0, 1),
                new BasicPositionJson(0, 2),
                new BasicPositionJson(1, 2),
                new BasicPositionJson(1, 3),
                new BasicPositionJson(2, 0),
                new BasicPositionJson(2, 3)
        );
        flagsJson = Arrays.asList(
                new BasicPositionJson(1, 2),
                new BasicPositionJson(2, 0),
                new BasicPositionJson(3, 1)
        );
        clearedJson = Arrays.asList(
                new BasicPositionJson(0, 0),
                new BasicPositionJson(0, 3),
                new BasicPositionJson(1, 0),
                new BasicPositionJson(1, 1),
                new BasicPositionJson(2, 2),
                new BasicPositionJson(3, 0)
        );
    }

    @Test
    public void testDeserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BasicGameJson basicGameJson = mapper.readValue(EXPECTED_JSON, BasicGameJson.class);

        assertEquals(GAME_START_TIME, basicGameJson.gameCreateTime());
        assertEquals(ELAPSED_TIME, basicGameJson.elapsedTime());

        assertConfigurationEquals(basicGameJson.configuration());
        assertPositionListEquals(minesJson, basicGameJson.mines());
        assertPositionListEquals(flagsJson, basicGameJson.flags());
        assertPositionListEquals(clearedJson, basicGameJson.cleared());
    }

    private void assertPositionListEquals(List<BasicPositionJson> expectedList, List<BasicPositionJson> actualList) {
        assertEquals(expectedList.size(), actualList.size());

        for(int i=0; i<expectedList.size(); i++){
            assertEquals(expectedList.get(i), actualList.get(i));
        }
    }

    private void assertConfigurationEquals(BasicConfigurationJson configuration) {
        assertEquals(WIDTH, configuration.width());
        assertEquals(HEIGHT, configuration.height());
        assertEquals(NUM_MINES, configuration.numMines());
    }
}
