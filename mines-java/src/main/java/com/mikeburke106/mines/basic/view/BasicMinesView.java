package com.mikeburke106.mines.basic.view;/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.view.MinesView;

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicMinesView implements MinesView {
    private Listener listener;
    private PositionView[][] positionViewGrid;
    private boolean gameOver;
    private int width;
    private int height;
    private TimeView timeView;
    private GameOverView gameOverView;

    public BasicMinesView(PositionView[][] positionViewGrid, int width, int height, Listener listener) {
        this.positionViewGrid = positionViewGrid;
        this.width = width;
        this.height = height;
        this.listener = listener;

        timeView = new TimeView();
        gameOverView = new GameOverView();
    }

    public void buttonPress(MinesView.ButtonValue buttonValue) {
        listener.onButtonClicked(buttonValue);
    }

    @Override
    public void positionCleared(int x, int y, int adjacent) {
        PositionView positionView = positionViewGrid[x][y];
        positionView.cleared = true;
        positionView.value = PositionView.Value.forAdjacentMines(adjacent);
        redraw();
    }

    @Override
    public void positionExploded(int x, int y) {
        PositionView positionView = positionViewGrid[x][y];
        positionView.cleared = true;
        positionView.value = PositionView.Value.MINE;
        redraw();
    }

    @Override
    public void positionFlagged(int x, int y) {
        PositionView positionView = positionViewGrid[x][y];
        positionView.flagged = true;
        positionView.value = PositionView.Value.FLAG;
        redraw();
    }

    @Override
    public void positionUnflagged(int x, int y) {
        PositionView positionView = positionViewGrid[x][y];
        positionView.flagged = false;
        positionView.value = PositionView.Value.FLAG;
        redraw();
    }

    @Override
    public void timeUpdate(long time) {
        timeView.time = time;
        redraw();
    }

    @Override
    public void gameWon() {
        gameOver = true;
        gameOverView.gameOverText = "YOU WON!";
        redraw();
    }

    @Override
    public void gameLost() {
        gameOver = true;
        gameOverView.gameOverText = "YOU LOST!";
        redraw();
    }

    private void redraw() {
        System.out.println("\n\n");

        if (gameOver) {
            System.out.println(gameOverView.toString());
        } else {
            for (int dy = 0; dy < height; dy++) {
                for (int dx = 0; dx < width; dx++) {
                    PositionView thisPosition = positionViewGrid[dx][dy];
                    System.out.println(thisPosition.toString());
                }
                System.out.print("\n");
            }
            System.out.println(timeView.toString());
        }
    }

    private static class PositionView {
        enum Value {
            UNKNOWN(' '),
            FLAG('P'),
            MINE('*'),
            ADJ0('0'),
            ADJ1('1'),
            ADJ2('2'),
            ADJ3('3'),
            ADJ4('4'),
            ADJ5('5'),
            ADJ6('6'),
            ADJ7('7'),
            ADJ8('8'),
            ADJ9('9'),;

            private final char charValue;

            Value(char charValue) {
                this.charValue = charValue;
            }

            public static Value forAdjacentMines(int adjacent) {
                switch (adjacent) {
                    case 0:
                        return ADJ0;
                    case 1:
                        return ADJ1;
                    case 2:
                        return ADJ2;
                    case 3:
                        return ADJ3;
                    case 4:
                        return ADJ4;
                    case 5:
                        return ADJ5;
                    case 6:
                        return ADJ6;
                    case 7:
                        return ADJ7;
                    case 8:
                        return ADJ8;
                    case 9:
                        return ADJ9;
                    default:
                        throw new RuntimeException("Invalid value for adjacent mines");
                }
            }
        }

        boolean cleared;
        boolean flagged;
        Value value;

        public PositionView() {
            this(Value.UNKNOWN, false, false);
        }

        public PositionView(Value value, boolean cleared, boolean flagged) {
            this.value = value;
            this.cleared = cleared;
            this.flagged = flagged;
        }

        @Override
        public String toString() {
            return "[" + value.charValue + "]";
        }
    }

    private static class TimeView {
        private long time;

        @Override
        public String toString() {
            return String.valueOf(time);
        }
    }

    private static class GameOverView {
        private String gameOverText;

        @Override
        public String toString() {
            return gameOverText;
        }
    }
}
