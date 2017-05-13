package com.swapnilhk.wakeupwiththesun.model;

import android.location.Location;

import java.util.Date;

/**
 * Created by swapn_000 on 5/1/2017.
 */

public class AlarmState {
    private boolean alarmOn;
    private double longitude;
    private double latitude;
    private String locationName;

    public boolean isAlarmOn() {
        return alarmOn;
    }

    public void setAlarmOn(boolean alarmOn) {
        this.alarmOn = alarmOn;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
