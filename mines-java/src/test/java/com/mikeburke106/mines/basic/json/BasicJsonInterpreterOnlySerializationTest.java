package com.mikeburke106.mines.basic.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
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
public class BasicJsonInterpreterOnlySerializationTest {
    private static final int TEST_INTEGER = 1248;
    private static final String TEST_INTEGER_JSON = "{\"integer\":1248}";

    static class TestJson {
        @JsonProperty
        private int integer;

        public TestJson(int integer) {
            this.integer = integer;
        }
    }

    private BasicJsonInterpreter<TestJson> basicJsonInterpreter;

    @Before
    public void setup() {
        basicJsonInterpreter = new BasicJsonInterpreter<>();
    }

    @Test
    public void testSerialization() throws JsonProcessingException {
        String jsonString = basicJsonInterpreter.toJson(new TestJson(TEST_INTEGER));
        assertEquals(TEST_INTEGER_JSON, jsonString);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDeserialization() throws IOException {
        basicJsonInterpreter.fromJson(TEST_INTEGER_JSON); // UnsupportedOperationException
    }
}
