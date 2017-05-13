package com.swapnilhk.wakeupwiththesun.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.swapnilhk.wakeupwiththesun.R;
import com.swapnilhk.wakeupwiththesun.model.AlarmState;

/**
 * Created by swapn_000 on 5/4/2017.
 */

public class AlarmStateUtil {

    public static void saveAlarmState(Activity context, AlarmState alarmState) {
        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(context.getString(R.string.prefsIsAlarmOn), alarmState.isAlarmOn());
        editor.putFloat(context.getString(R.string.prefsLocationLatitude), (float) alarmState.getLatitude());
        editor.putFloat(context.getString(R.string.prefsLocationLongitude), (float) alarmState.getLongitude());
        editor.putString(context.getString(R.string.prefsLocationName), alarmState.getLocationName());
        editor.commit();
    }

    public static AlarmState readAlarmState(Activity context) {
        AlarmState alarmState = new AlarmState();
        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        alarmState.setAlarmOn(sharedPref.getBoolean(context.getString(R.string.prefsIsAlarmOn), false));
        alarmState.setLongitude(sharedPref.getFloat(context.getString(R.string.prefsLocationLongitude), (float) 0.0));
        alarmState.setLatitude(sharedPref.getFloat(context.getString(R.string.prefsLocationLatitude), (float) 0.0));
        alarmState.setLocationName(sharedPref.getString(context.getString(R.string.prefsLocationName), null));
        return alarmState;
    }

}
