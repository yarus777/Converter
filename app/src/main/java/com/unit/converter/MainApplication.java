package com.unit.converter;

import android.support.multidex.MultiDexApplication;

import com.inappertising.ads.ExternalAnalyticsManager;
import com.unit.converter.utils.CrashReportHandler;

public class MainApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReportHandler.attach(this);
        ExternalAnalyticsManager.initAllMetrics(this);
//        D.setDebug(true, "w4ty6e");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
