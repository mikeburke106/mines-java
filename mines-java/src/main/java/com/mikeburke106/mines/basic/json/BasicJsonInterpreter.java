package com.mikeburke106.mines.basic.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Basic implementation of a generic JSON interpreter.
 *
 * Created by Mike Burke on 4/8/17.
 */
public class BasicJsonInterpreter<T> implements JsonInterpreter<T> {
    private final ObjectMapper mapper;
    private final Class<T> classType;

    /**
     * Constructor.
     *
     * <b>NOTE: This constructor is for serialization only.</b>
     */
    public BasicJsonInterpreter() {
        this(null);
    }

    /**
     * Constructor.
     *
     * @param classType The type of class for deserialization
     */
    public BasicJsonInterpreter(Class<T> classType) {
        this(classType, new ObjectMapper());
    }

    /**
     * Constructor.
     *
     * @param classType The type of class for deserialization
     * @param mapper Jackson mapper object
     */
    public BasicJsonInterpreter(Class<T> classType, ObjectMapper mapper) {
        this.classType = classType;
        this.mapper = mapper;
    }

    @Override
    public String toJson(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    @Override
    public T fromJson(String json) throws IOException {
        if(classType == null){
            throw new UnsupportedOperationException("Class type was null, so deserialization cannot be completed.");
        }

        return mapper.readValue(json, classType);
    }
}
