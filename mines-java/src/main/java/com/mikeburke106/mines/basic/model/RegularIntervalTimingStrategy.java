package com.mikeburke106.mines.basic.model;/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import com.mikeburke106.mines.api.model.Game;

import java.util.concurrent.ThreadFactory;

/**
 * Timing strategy which starts at a particular time and counts
 * up at a regular interval.
 * <p>
 * Created by Mike Burke on 4/8/17.
 */
public class RegularIntervalTimingStrategy implements Game.TimingStrategy {

    private final IncrementingSecondsRunnable timerRunnable;
    private ThreadFactory threadFactory;
    private Thread timerThread;
    private boolean running;

    /**
     * Constructor.  Assumes a start time of 0.
     *
     * @param interval Duration between time updates (in milliseconds)
     */
    public RegularIntervalTimingStrategy(long interval) {
        this(interval, 0L);
    }

    /**
     * Constructor.  Assumes a generic thread factory and listener.
     *
     * @param interval  Duration between time updates (in milliseconds)
     * @param startTime Start offset of the timer (in milliseconds)
     */
    public RegularIntervalTimingStrategy(long interval, long startTime) {
        this(interval, startTime, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        }, Game.TimingStrategy.Listener.DEFAULT);
    }

    /**
     * Constructor.
     *
     * @param interval      Duration between time updates (in milliseconds)
     * @param startTime     Start offset of the timer (in milliseconds)
     * @param threadFactory Factory for creating timer threads
     * @param listener      Listener to be notified of interval updates
     */
    public RegularIntervalTimingStrategy(long interval, long startTime, ThreadFactory threadFactory, Listener listener) {
        this.timerRunnable = new IncrementingSecondsRunnable(interval, startTime, listener);
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

    @Override
    public void setListener(Listener listener) {
        this.timerRunnable.listener = listener;
    }

    @Override
    public String toString() {
        return "RegularIntervalTimingStrategy{" +
                "timerThread=" + timerThread +
                "currentTime=" + timerRunnable.currentTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegularIntervalTimingStrategy that = (RegularIntervalTimingStrategy) o;

        return timerThread.equals(that.timerThread) &&
                this.getCurrentTime() == that.getCurrentTime();
    }

    @Override
    public int hashCode() {
        int result = (int) (timerRunnable.currentTime ^ (timerRunnable.currentTime >>> 32));
        return 31 * result * timerThread.hashCode();
    }

    static class IncrementingSecondsRunnable implements Runnable {
        private static final int INTERVAL = 50;

        private volatile long currentTime;
        private final long interval;
        private Listener listener;
        private boolean running;

        public IncrementingSecondsRunnable(long interval, long startTime, Listener listener) {
            this.interval = interval;
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

                    if ((currentTime % interval) == 0) {
                        listener.timeUpdate(currentTime);
                    }
                }
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }
}
