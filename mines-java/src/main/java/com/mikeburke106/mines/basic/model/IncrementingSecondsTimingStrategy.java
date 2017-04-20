package com.mikeburke106.mines.basic.model;/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.model.Game;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Mike Burke on 4/8/17.
 */
public class IncrementingSecondsTimingStrategy implements Game.TimingStrategy {

    private final IncrementingSecondsRunnable timerRunnable;
    private ThreadFactory threadFactory;
    private Thread timerThread;
    private boolean running;

    public IncrementingSecondsTimingStrategy(int secondsIncrement, Listener listener) {
        this(secondsIncrement, 0L, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        }, listener);
    }

    public IncrementingSecondsTimingStrategy(int secondsIncrement, long startTime, ThreadFactory threadFactory, Listener listener) {
        this.timerRunnable = new IncrementingSecondsRunnable(secondsIncrement, startTime, listener);
        this.threadFactory = threadFactory;
    }

    @Override
    public void start() {
        if (!running) {
            running = true;
            timerThread = threadFactory.newThread(timerRunnable);
            timerThread.start();
        }
    }

    @Override
    public void pause() {
        if (running) {
            running = false;
            timerThread.interrupt();
            timerThread = null;
        }
    }

    @Override
    public long getCurrentTime() {
        return timerRunnable.currentTime;
    }

    static class IncrementingSecondsRunnable implements Runnable {
        private static final int INTERVAL = 50;

        private volatile long currentTime;
        private final int secondsIncrement;
        private Listener listener;
        private boolean running;

        public IncrementingSecondsRunnable(int secondsIncrement, long startTime, Listener listener) {
            this.secondsIncrement = secondsIncrement;
            this.currentTime = startTime;
            this.listener = listener;
        }

        @Override
        public void run() {
            running = true;
            try {
                while (running) {
                    Thread.sleep(INTERVAL);
                    currentTime += INTERVAL;

                    if ((currentTime % (secondsIncrement * 1000)) == 0) {
                        listener.timeUpdate(currentTime);
                    }
                }
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }
}
