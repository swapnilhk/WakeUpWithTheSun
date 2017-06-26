package com.swapnilhk.wakeupwiththesun.model;

/**
 * Created by swapn_000 on 6/26/2017.
 */

public class Schedule {
    private long sunriseTime;
    private long sunsetTime;
    private boolean isAllDay;
    private boolean isAllNight;

    public Schedule(long sunriseTime, long sunsetTime, boolean isAllDay, boolean isAllNight) {
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.isAllDay = isAllDay;
        this.isAllNight = isAllNight;
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

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public boolean isAllNight() {
        return isAllNight;
    }

    public void setAllNight(boolean allNight) {
        isAllNight = allNight;
    }
}
