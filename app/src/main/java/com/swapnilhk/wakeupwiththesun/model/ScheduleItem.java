package com.swapnilhk.wakeupwiththesun.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by swapn_000 on 5/14/2017.
 */

public class ScheduleItem {
    private double longitude;
    private double latitude;
    private Date date;

    public ScheduleItem(double longitude, double latitude, Date date) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Date getDate() {
        return date;
    }
}
