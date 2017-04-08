package com.mikeburke106.mines.basic.json;

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
public class BasicPositionJsonDeserializeTest {
    private static final int X = 0;
    private static final int Y = 9;
    private static final String EXPECTED_JSON = "{\"x\":0,\"y\":9}";

    @Test
    public void testDeserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BasicPositionJson basicPositionJson = mapper.readValue(EXPECTED_JSON, BasicPositionJson.class);

        assertEquals(X, basicPositionJson.x());
        assertEquals(Y, basicPositionJson.y());
    }
}
