package com.swapnilhk.wakeupwiththesun.util;

import com.swapnilhk.wakeupwiththesun.model.Schedule;
import com.swapnilhk.wakeupwiththesun.model.ScheduleQuery;

import java.util.Calendar;

/**
 * Created by swapn_000 on 5/13/2017.
 */

public class AlarmTimeUtil {

    private static double getDeclination(ScheduleQuery scheduleQuery){
        Calendar cal = Calendar.getInstance();
        cal.setTime(scheduleQuery.getDate());
        return - Constants.MAX_DECLINATION * Math.cos(360.0/355 * (cal.get(Calendar.DAY_OF_YEAR) + 10) * Math.PI / 180);
    }
    private static long getDayLength(ScheduleQuery scheduleQuery){
        return (long)(Math.acos(-Math.tan(scheduleQuery.getLatitude()*Math.PI/180) * Math.tan(getDeclination(scheduleQuery)*Math.PI/180))
                / Math.PI * Constants.DAY_LENGTH);
    }
    public static Schedule getSchedule(ScheduleQuery scheduleQuery){
        long offset = Calendar.getInstance().getTimeZone().getOffset(scheduleQuery.getDate().getTime()); // Offset in milliseconds with reference to GMT
        long correction = (long)(Constants.DAY_LENGTH * scheduleQuery.getLongitude()/360); // Correction for offset in milliseconds depending upon longitude
        Calendar cal = Calendar.getInstance();
        cal.setTime(scheduleQuery.getDate());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long sunriseTime = cal.getTime().getTime()/* Midnight */ + (long)(Constants.DAY_LENGTH - getDayLength(scheduleQuery)) / 2 + (offset - correction);
        long sunsetTime = cal.getTime().getTime() + Constants.DAY_LENGTH/* Midnight */ - (long)(Constants.DAY_LENGTH - getDayLength(scheduleQuery)) / 2 + (offset - correction);
        return new Schedule(sunriseTime, sunsetTime);
    }
}
