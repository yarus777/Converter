package com.unit.converter;

import android.support.multidex.MultiDexApplication;

import com.inappertising.ads.ExternalAnalyticsManager;

public class MainApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ExternalAnalyticsManager.initAllMetrics(this);
//        D.setDebug(true, "w4ty6e");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
