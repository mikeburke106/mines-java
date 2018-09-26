package com.mikeburke106.mines.api.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Represents the field of play, consisting of a grid of positions.
 * <p>
 * Created by Mike Burke on 3/31/17.
 */
public interface Field {
    /**
     * @return The field's configuration.
     */
    Configuration configuration();

    /**
     * Clears a position on the field.
     *
     * @param position The position to clear
     * @return Whether or not the cleared position was a mine.
     */
    boolean clear(Position position);

    /**
     * Flags a position on the field.
     *
     * @param position The position to flag
     * @return The new flag value
     */
    boolean flag(Position position);

    /**
     * Checks if the given position is currently flagged.
     *
     * @param position The position to check
     * @return True if flagged, false otherwise
     */
    boolean isFlag(Position position);

    /**
     * Checks if the given position is a mine.
     *
     * @param position The position to check
     * @return True if a mine, false otherwise
     */
    boolean isMine(Position position);

    /**
     * Represents the configuration of a Field.
     */
    interface Configuration {
        /**
         * @return The positionPool of the field.
         */
        Position.Pool positionPool();

        /**
         * @return The number of mines on the field.
         */
        int numMines();

        /**
         * Strategy-pattern to validate inputs to the factory method.
         */
        interface ValidationStrategy {
            /**
             * Validates the input parameters against business logic.
             *
             * @param configuration The desired configuration
             * @throws InvalidConfigurationException If the input parameters are invalid according to business logic.
             */
            void validate(Configuration configuration) throws InvalidConfigurationException;
        }

        /**
         * Exception that is thrown if the Factory is given parameters that would represent an invalid Field instance.
         */
        class InvalidConfigurationException extends Exception {
            public InvalidConfigurationException() {
            }

            public InvalidConfigurationException(String message) {
                super(message);
            }

            public InvalidConfigurationException(String message, Throwable cause) {
                super(message, cause);
            }

            public InvalidConfigurationException(Throwable cause) {
                super(cause);
            }

            public InvalidConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
                super(message, cause, enableSuppression, writableStackTrace);
            }
        }
    }

    /**
     * Factory for generating new Field instances.
     */
    interface Factory {
        /**
         * Generates a new Field instance.
         *
         * @param configuration The desired configuration of the Field instance to create.
         * @return The new Field instance.
         * @throws Configuration.InvalidConfigurationException If any of the input parameters are invalid.
         */
        Field newInstance(Configuration configuration) throws Configuration.InvalidConfigurationException;

        Field newInstance(Configuration configuration, int initialX, int initialY) throws Configuration.InvalidConfigurationException;
    }
}
