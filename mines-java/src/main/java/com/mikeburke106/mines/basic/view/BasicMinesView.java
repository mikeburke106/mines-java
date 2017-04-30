package com.mikeburke106.mines.basic.view;/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.model.Position;
import com.mikeburke106.mines.api.view.MinesView;

import java.util.Map;

/**
 * Basic implementation of a text-based mines view,
 * which will print state to System.out.
 *
 * Created by Mike Burke on 4/8/17.
 */
public class BasicMinesView implements MinesView {
    private Listener listener;
    private Map<Position, PositionView> positionViewMap;
    private Position.Pool positionPool;
    private boolean gameOver;
    private TimeView timeView;
    private GameOverView gameOverView;

    public BasicMinesView(Map<Position, PositionView> positionViewMap, Position.Pool positionPool, Listener listener) {
        this(positionViewMap, positionPool, 0L, listener);
    }

    public BasicMinesView(Map<Position, PositionView> positionViewMap, Position.Pool positionPool, long startTime,
                          Listener listener) {
        this.positionViewMap = positionViewMap;
        this.positionPool = positionPool;
        this.listener = listener;

        timeView = new TimeView(startTime);
        gameOverView = new GameOverView();
    }

    public void buttonPress(InputValue inputValue) {
        listener.onInputValueClicked(inputValue);
        redraw();
    }

    private PositionView getPositionView(int x, int y) {
        Position position = positionPool.atLocation(x, y);
        return positionViewMap.get(position);
    }

    @Override
    public void positionCleared(int x, int y, int adjacent) {
        PositionView positionView = getPositionView(x, y);
        positionView.cleared = true;
        positionView.value = PositionView.Value.forAdjacentMines(adjacent);
        redraw();
    }

    @Override
    public void positionExploded(int x, int y) {
        PositionView positionView = getPositionView(x, y);
        positionView.cleared = true;
        positionView.value = PositionView.Value.MINE;
        redraw();
    }

    @Override
    public void positionFlagged(int x, int y) {
        PositionView positionView = getPositionView(x, y);
        positionView.flagged = true;
        positionView.value = PositionView.Value.FLAG;
        redraw();
    }

    @Override
    public void positionUnflagged(int x, int y) {
        PositionView positionView = getPositionView(x, y);
        positionView.flagged = false;
        positionView.value = PositionView.Value.FLAG;
        redraw();
    }

    @Override
    public void timeUpdate(long time) {
        timeView.time = time;
    }

    @Override
    public void gameWon() {
        gameOver = true;
        gameOverView.gameOverText = "YOU WON!";
        System.out.println("\n\n" + gameOverView.toString() + "\n\n");
    }

    @Override
    public void gameLost() {
        gameOver = true;
        gameOverView.gameOverText = "YOU LOST!";
        System.out.println("\n\n" + gameOverView.toString() + "\n\n");
    }

    private synchronized void redraw() {
        System.out.println("\n");

        if (!gameOver) {
            int i = 0;
            for (Position position : positionPool) {
                PositionView thisPosition = positionViewMap.get(position);
                System.out.print((i == 0 ? "\n" : "") + thisPosition.toString());

                final int maybeNextI = i + 1;
                final int width = positionPool.width();
                i = (maybeNextI % width) == 0 ? 0 : maybeNextI;
            }

            System.out.println("\n\n\t\t" + timeView.toString() + "\n\n");
        }
    }

    public static class PositionView {
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

        TimeView(long time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return String.valueOf(time/1000);
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
