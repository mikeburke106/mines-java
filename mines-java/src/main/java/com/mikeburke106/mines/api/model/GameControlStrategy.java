package com.mikeburke106.mines.api.model;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike Burke on 4/8/17.
 */
public interface GameControlStrategy {
    interface Listener {
        enum PositionAction {
            CLEAR,
            EXPLODE,
            FLAG,
            UNFLAG,;
        }

        void positionUpdate(PositionAction positionAction, int x, int y);

        void timeUpdate(long time);

        void gameWon();

        void gameLost();
    }

    void clear(int x, int y);

    void toggleFlag(int x, int y);

}
