package com.swapnilhk.wakeupwiththesun.util;

import com.swapnilhk.wakeupwiththesun.model.ScheduleItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by swapn_000 on 5/13/2017.
 */

public class AlarmTimeUtil {
    private static final double MAX_DECLINATION = 23.45;
    private static final long DAY_LENGTH = 24L * 60 * 60 * 1000;
    private static double getDeclination(ScheduleItem scheduleItem){
        Calendar cal = Calendar.getInstance();
        cal.setTime(scheduleItem.getDate());
        return -MAX_DECLINATION * Math.cos(360.0/355 * (cal.get(Calendar.DAY_OF_YEAR) + 10) * Math.PI / 180);
    }
    private static long getDayLength(ScheduleItem scheduleItem){
        return (long)(Math.acos(-Math.tan(scheduleItem.getLatitude()*Math.PI/180) * Math.tan(getDeclination(scheduleItem)*Math.PI/180))
                / Math.PI * DAY_LENGTH);
    }
    public static String getSunriseTime(ScheduleItem scheduleItem){
        long offset = Calendar.getInstance().getTimeZone().getOffset(scheduleItem.getDate().getTime()); // Offset in milliseconds with reference to GMT
        long correction = (long)(DAY_LENGTH * scheduleItem.getLongitude()/360); // Correction for offset in milliseconds depending upon longitude
        Calendar cal = Calendar.getInstance();
        cal.setTime(scheduleItem.getDate());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long sunriseTime = cal.getTime().getTime()/* Midnight */ + (long)(DAY_LENGTH - getDayLength(scheduleItem)) / 2 + (offset - correction);
        long sunsetTime = cal.getTime().getTime() + DAY_LENGTH/* Midnight */ - (long)(DAY_LENGTH - getDayLength(scheduleItem)) / 2 + (offset - correction);
        return ("Sunreise : " + new SimpleDateFormat("h:mm a").format(new Date(sunriseTime)) + " Sunset : " + new SimpleDateFormat("h:mm a").format(new Date(sunsetTime)));
    }
}
