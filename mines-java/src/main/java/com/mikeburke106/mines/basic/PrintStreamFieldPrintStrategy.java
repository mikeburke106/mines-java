package com.mikeburke106.mines.basic;

import com.mikeburke106.mines.model.Field;
import com.mikeburke106.mines.model.FieldPrintStrategy;
import com.mikeburke106.mines.model.Position;

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
        final int height = positionPool.height();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Position position = positionPool.atLocation(x, y);
                final String positionText = field.isMine(position) ? "[x]" : "[ ]";
                outStream.print(positionText);
            }
            outStream.print("\n");
        }
    }
}
