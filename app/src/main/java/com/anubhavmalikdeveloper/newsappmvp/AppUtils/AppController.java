package com.anubhavmalikdeveloper.newsappmvp.AppUtils;

import android.app.Application;


public class AppController extends Application {
    private static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;

    public AppController() {
        super();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
