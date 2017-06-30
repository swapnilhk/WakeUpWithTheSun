package com.swapnilhk.wakeupwiththesun.util;

import android.util.Log;

import com.swapnilhk.wakeupwiththesun.model.Schedule;

/**
 * Created by swapn_000 on 6/26/2017.
 */

public class ColorUtil {
    /**
     * Gets color between 0x0000ff 0xff0000
     * depending upon the length of the day
     * @param schedule
     * @return integer value of the color
     * */
    public static int getColorByDayLength(Schedule schedule){
        double frac = ((schedule.getSunsetTime() - schedule.getSunriseTime()) / (double) Constants.DAY_LENGTH);
        int red = 0xff;
        int blue = 0xff;
        red = schedule.isAllDay() ? 0xff0000 : schedule.isAllNight() ? 0 : (int)(red * frac) << 16;
        blue = schedule.isAllNight() ? 0xff : schedule.isAllDay() ? 0 : ((int)(blue * (1 - frac)));
        int ret = 0xff000000 + red + blue;
        return ret;
    }
}
