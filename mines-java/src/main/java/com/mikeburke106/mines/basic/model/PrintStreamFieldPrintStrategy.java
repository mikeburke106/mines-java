package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Field;
import com.mikeburke106.mines.api.model.FieldPrintStrategy;
import com.mikeburke106.mines.api.model.Position;

import java.io.PrintStream;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Prints the field out to text.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class PrintStreamFieldPrintStrategy implements FieldPrintStrategy {
    private final PrintStream outStream;

    public PrintStreamFieldPrintStrategy(PrintStream outStream) {
        this.outStream = outStream;
    }

    @Override
    public void printField(Field field) {
        final Field.Configuration fieldConfiguration = field.configuration();
        final Position.Pool positionPool = fieldConfiguration.positionPool();
        final int width = positionPool.width();

        int index = 0;
        for (Position position : positionPool) {
            final String positionText = field.isMine(position) ? "[x]" : "[ ]";
            outStream.print(positionText);
            index++;
            if ((index % width) == 0) {
                outStream.print("\n");
            }
        }
    }

    @Override
    public String toString() {
        return "PrintStreamFieldPrintStrategy{" +
                "outStream=" + outStream +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrintStreamFieldPrintStrategy that = (PrintStreamFieldPrintStrategy) o;

        return outStream.equals(that.outStream);
    }

    @Override
    public int hashCode() {
        return outStream.hashCode();
    }
}
