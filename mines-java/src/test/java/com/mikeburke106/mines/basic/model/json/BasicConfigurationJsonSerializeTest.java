package com.mikeburke106.mines.basic.model.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicConfigurationJsonSerializeTest {
    private static final int WIDTH = 204;
    private static final int HEIGHT = 914;
    private static final int NUM_MINES = 184;
    private static final String EXPECTED_JSON = "{\"width\":204,\"height\":914,\"numMines\":184}";

    private BasicConfigurationJson basicConfigurationJson;

    @Before
    public void setup() {
        basicConfigurationJson = new BasicConfigurationJson(WIDTH, HEIGHT, NUM_MINES);
    }

    @Test
    public void testSerialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(basicConfigurationJson);
        assertEquals(EXPECTED_JSON, jsonString);
    }
}
