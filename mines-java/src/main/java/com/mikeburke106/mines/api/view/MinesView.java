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
    enum ButtonValue{
        NEW_GAME,
        SAVE_GAME,
        RESTORE_GAME,
        RESTART_GAME,
        END_GAME,
        ;
    }

    interface Listener {
        void onItemClicked(int x, int y);
        void onItemLongClicked(int x, int y);
        void onButtonClicked(ButtonValue buttonValue);
    }
}
