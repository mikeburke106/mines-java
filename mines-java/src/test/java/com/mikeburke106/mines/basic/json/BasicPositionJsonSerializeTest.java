package com.mikeburke106.mines.basic.json;

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
public class BasicPositionJsonSerializeTest {
    private static final int X = 0;
    private static final int Y = 9;
    private static final String EXPECTED_JSON = "{\"x\":0,\"y\":9}";

    private BasicPositionJson basicPositionJson;

    @Before
    public void setup() {
        basicPositionJson = new BasicPositionJson(X, Y);
    }

    @Test
    public void testSerialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(basicPositionJson);
        assertEquals(EXPECTED_JSON, jsonString);
    }
}
