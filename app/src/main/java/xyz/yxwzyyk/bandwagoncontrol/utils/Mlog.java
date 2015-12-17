package xyz.yxwzyyk.bandwagoncontrol.utils;

import android.util.Log;

/**
 * Created by yyk on 12/12/15.
 */
public class Mlog {
    public static String TAG = "MyLog";
    public static boolean DEBUG = true;

    public static void logI(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void logI(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }
}
