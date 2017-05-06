package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Field;
import com.mikeburke106.mines.api.model.Position;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Implements a basic Field implementation using position sets for flags and mines.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class BasicField implements Field {
    private final Configuration configuration;
    private final Set<Position> mines;
    private final Set<Position> flags;

    public BasicField(Configuration configuration, Set<Position> mines, Set<Position> flags) {
        this.configuration = configuration;
        this.mines = mines;
        this.flags = flags;
    }

    @Override
    public Configuration configuration() {
        return this.configuration;
    }

    @Override
    public boolean clear(Position position) {
        return this.mines.contains(position);
    }

    @Override
    public boolean flag(Position position) {
        boolean isFlagged = this.flags.contains(position);

        if (isFlagged) {
            this.flags.remove(position);
        } else {
            this.flags.add(position);
        }

        return !isFlagged;
    }

    @Override
    public boolean isFlag(Position position) {
        return this.flags.contains(position);
    }

    @Override
    public boolean isMine(Position position) {
        return this.mines.contains(position);
    }

    @Override
    public String toString() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStreamFieldPrintStrategy printer = new PrintStreamFieldPrintStrategy(printStream);
        printer.printField(this);
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicField that = (BasicField) o;

        if (!configuration.equals(that.configuration)) return false;
        if (!mines.equals(that.mines)) return false;
        return flags.equals(that.flags);
    }

    @Override
    public int hashCode() {
        int result = configuration.hashCode();
        result = 31 * result + mines.hashCode();
        result = 31 * result + flags.hashCode();
        return result;
    }
}

