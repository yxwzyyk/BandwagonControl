package xyz.yxwzyyk.bandwagoncontrol.app

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by yyk on 2/29/16.
 */
class MainApplication: Application() {
    companion object {
        var instance: MainApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AnalyticsTrackers.initialize(this)
    }
}