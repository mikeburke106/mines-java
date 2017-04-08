package com.mikeburke106.mines.basic.model.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicConfigurationJsonDeserializeTest {
    private static final int WIDTH = 204;
    private static final int HEIGHT = 914;
    private static final int NUM_MINES = 184;
    private static final String EXPECTED_JSON = "{\"width\":204,\"height\":914,\"numMines\":184}";

    @Test
    public void testDeserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BasicConfigurationJson basicConfigurationJson = mapper.readValue(EXPECTED_JSON, BasicConfigurationJson.class);

        assertEquals(WIDTH, basicConfigurationJson.width());
        assertEquals(HEIGHT, basicConfigurationJson.height());
        assertEquals(NUM_MINES, basicConfigurationJson.numMines());
    }
}
