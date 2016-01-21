package xyz.yxwzyyk.bandwagoncontrol.app;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import xyz.yxwzyyk.bandwagoncontrol.R;

/**
 * Created by yyk on 16/1/21.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AnalyticsTrackers.initialize(this);
    }

//    private Tracker mTracker;
//
//    /**
//     * Gets the default {@link Tracker} for this {@link Application}.
//     * @return tracker
//     */
//    synchronized public Tracker getDefaultTracker() {
//        if (mTracker == null) {
//            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
//            mTracker = analytics.newTracker(R.xml.app_tracker);
//        }
//        return mTracker;
//    }
}
