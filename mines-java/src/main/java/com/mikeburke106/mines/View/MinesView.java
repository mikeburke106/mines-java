package com.mikeburke106.mines.View;/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike Burke on 4/8/17.
 */
public interface MinesView {
    interface Listener {
        void clearRequest(int x, int y);

        void flagRequest(int x, int y);

        void newGame();

        void endGame();

        void restartGame();

        void saveGame(String filename);

        void restoreGame(String filename);

        Listener DEFAULT = new Listener() {
            @Override
            public void clearRequest(int x, int y) {
            }

            @Override
            public void flagRequest(int x, int y) {
            }

            @Override
            public void newGame() {
            }

            @Override
            public void endGame() {
            }

            @Override
            public void restartGame() {
            }

            @Override
            public void saveGame(String filename) {
            }

            @Override
            public void restoreGame(String filename) {
            }
        };
    }

    interface ItemClickListener {
        void onItemClicked(int x, int y);

        void onItemLongClicked(int x, int y);
    }

    void setListener(Listener listener);
}
