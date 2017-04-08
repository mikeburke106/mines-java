package com.mikeburke106.mines.basic.view;/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.View.MinesView;

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicMinesView implements MinesView {
    private Listener listener = Listener.DEFAULT;

    private final ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onItemClicked(int x, int y) {
            listener.clearRequest(x, y);
        }

        @Override
        public void onItemLongClicked(int x, int y) {
            listener.flagRequest(x, y);
        }
    };

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
