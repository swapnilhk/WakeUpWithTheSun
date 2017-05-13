package com.swapnilhk.wakeupwiththesun.model;

/**
 * Created by swapn_000 on 5/13/2017.
 */

public class AlarmTime {
    private String hour;
    private String minute;

    public AlarmTime(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return hour + ":" + minute + " A.M.";
    }
}
