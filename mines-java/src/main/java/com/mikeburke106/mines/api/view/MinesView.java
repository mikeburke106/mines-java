package com.mikeburke106.mines.api.view;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.model.GameControlStrategy;

/**
 * Interface definition of a view that contains the entire
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public interface MinesView extends GameControlStrategy.Listener {
    /**
     * Enumeration representing all inputs supported by the game controller
     * strategy.
     */
    enum InputValue {
        NEW_GAME,
        SAVE_GAME,
        RESTORE_GAME,
        RESTART_GAME,
        END_GAME,;
    }

    /**
     * Listener for the view.
     */
    interface Listener {
        /**
         * Called when a position on the field was clicked.
         *
         * @param x The clicked position's x-coordinate
         * @param y The clicked position's y-coordinate
         */
        void onPositionClicked(int x, int y);

        /**
         * Called when a position on the field was long-clicked.
         *
         * @param x The clicked position's x-coordinate
         * @param y The clicked position's y-coordinate
         */
        void onPositionLongClicked(int x, int y);

        /**
         * Called when one of the values of the InputValue enum
         * has been selected within the view or UI.
         *
         * @param inputValue The enum value of the selected item
         */
        void onInputValueClicked(InputValue inputValue);
    }
}
