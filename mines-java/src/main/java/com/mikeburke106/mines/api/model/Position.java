package com.mikeburke106.mines.api.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Represents an (x,y) position.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public interface Position {
    /**
     * @return The x position.
     */
    int x();

    /**
     * @return The y position.
     */
    int y();

    /**
     * An interface for a pool of position objects.
     */
    interface Pool extends Iterable<Position> {
        /**
         * @param x The x position
         * @param y The y position
         * @return The Position object for the input position.
         */
        Position atLocation(int x, int y);

        /**
         * @return The number of Positions in the pool.
         */
        int size();

        /**
         * @return The width of Positions in the pool.
         */
        int width();

        /**
         * @return The height of Positions in the pool.
         */
        int height();
    }

    /**
     * Factory for generating Positions.
     */
    interface Factory {
        /**
         * Creates a new Position with input values.
         *
         * @param x The x position to set
         * @param y The y position to set
         * @return A new instance of a Position.
         */
        Position newInstance(int x, int y);
    }

    /**
     * Provides instances of Position.
     */
    interface Provider {
        /**
         * @return The next position available from the provider
         * @throws NoPositionsAvailableException If the provider is unable to provide further positions.
         */
        Position nextPosition() throws NoPositionsAvailableException;

        /**
         * Factory for generating Providers.
         */
        interface Factory {
            Provider newInstance(Pool positionPool);
        }

        /**
         * Exception is thrown if the provider is unable to provide further positions.
         */
        class NoPositionsAvailableException extends Exception {
            public NoPositionsAvailableException() {
            }

            public NoPositionsAvailableException(String message) {
                super(message);
            }

            public NoPositionsAvailableException(String message, Throwable cause) {
                super(message, cause);
            }

            public NoPositionsAvailableException(Throwable cause) {
                super(cause);
            }

            public NoPositionsAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
                super(message, cause, enableSuppression, writableStackTrace);
            }
        }
    }
}
