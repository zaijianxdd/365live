package com.zjxdd.mvp.base;

import android.app.Application;
import android.content.Context;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:04
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
