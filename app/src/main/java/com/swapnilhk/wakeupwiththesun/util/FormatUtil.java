package com.swapnilhk.wakeupwiththesun.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by swapn_000 on 6/26/2017.
 * Formats various data types like Date
 * into string of required format
 */

public class FormatUtil {

    /**
     * Formats Date into string of format yyyy-MM-dd
     * @param  date the date containing the date information
     * @return string of required format
     * */
    public static String formatDate(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * Formats Date into string of format "h:mm a" signifying time
     * as captured by the supplied date
     * @param  date the date containing the date information
     * @return string of required format
     * */
    public static String formatTime(Date date){
        return new SimpleDateFormat("h:mm a").format(date);
    }
}
