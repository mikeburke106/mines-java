package com.mikeburke106.mines.basic.json;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * Definition of interpreting POJOs to and from JSON strings.
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public interface JsonInterpreter<T> {
    /**
     * Generates a JSON string for the given Java object.
     *
     * @param object The object to interpret
     * @return The JSON string representing the object
     */
    String toJson(T object) throws JsonProcessingException;

    /**
     * Generates a Java object from the given JSON string.
     *
     * @param json The JSON string representing the object
     * @return The object generated from the JSON string
     */
    T fromJson(String json) throws IOException;
}
