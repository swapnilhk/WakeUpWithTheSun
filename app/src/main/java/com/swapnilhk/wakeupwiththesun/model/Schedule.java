package com.swapnilhk.wakeupwiththesun.model;

/**
 * Created by swapn_000 on 6/26/2017.
 */

public class Schedule {
    private long sunriseTime;
    private long sunsetTime;

    public Schedule(long sunriseTime, long sunsetTime) {
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
    }

    public long getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public long getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }
}
