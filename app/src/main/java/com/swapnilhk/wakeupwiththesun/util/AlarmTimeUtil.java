package com.swapnilhk.wakeupwiththesun.util;

import com.swapnilhk.wakeupwiththesun.model.AlarmTime;
import com.swapnilhk.wakeupwiththesun.model.ScheduleItem;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by swapn_000 on 5/13/2017.
 */

public class AlarmTimeUtil {
    private static final double MAX_DECLINATION = 23.45;
    private static double getDeclination(ScheduleItem scheduleItem){
        Calendar cal = Calendar.getInstance();
        cal.setTime(scheduleItem.getDate());
        return -MAX_DECLINATION * Math.cos(360.0/355 * (cal.get(Calendar.DAY_OF_YEAR) + 10) * Math.PI / 180);
    }
    private static double getDayLength(ScheduleItem scheduleItem){
        return Math.acos(-Math.tan(scheduleItem.getLatitude()*Math.PI/180) * Math.tan(getDeclination(scheduleItem)*Math.PI/180))
                / Math.PI * 24 * 60 * 60;
    }
    public static AlarmTime getSunriseTime(ScheduleItem scheduleItem){
        long offset = Calendar.getInstance().getTimeZone().getOffset(scheduleItem.getDate().getTime()) / 1000; // Offset in milliseconds with reference to GMT
        double correction = 24*60*60 * scheduleItem.getLongitude()/360; // Correction for offset in milliseconds depending upon longitude
        double alarmTime = 12 * 60 * 60 - getDayLength(scheduleItem) / 2 + (offset - correction);
        return new AlarmTime(String.valueOf((long)(alarmTime/60/60)),
                String.valueOf((long)((alarmTime - ((long)(alarmTime/60/60)) * 60 * 60)) / 60));
    }
}
