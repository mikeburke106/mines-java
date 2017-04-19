package com.mikeburke106.mines.api.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * A strategy for printing the field to various sources.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public interface FieldPrintStrategy {
    /**
     * Prints the field.
     *
     * @param field The field to print.
     */
    void printField(Field field);
}

