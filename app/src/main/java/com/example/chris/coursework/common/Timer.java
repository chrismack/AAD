package com.example.chris.coursework.common;

import java.util.concurrent.TimeUnit;

/**
 * Created by Chris on 07/02/2018.
 */

public class Timer {

    private long startTime = -1;
    private long finishTime = -1;

    public Timer() {}

    public void startTimer(long time) {
        this.startTime = time;
    }

    public void endTimer(long time) {
        this.finishTime = time;
    }

    public long getTimeElapsed() {
        if(startTime > -1 && finishTime > -1) {
            return finishTime - startTime;
        }
        return -1;
    }

    public long getTimeElapsedSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds(getTimeElapsed());
    }
}
