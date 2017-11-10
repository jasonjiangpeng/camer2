package com.example.administrator.testdemo;

import android.app.Application;

import com.example.administrator.testdemo.face.VolleyHelp;

/**
 * Created by Administrator on 2017/8/14.
 */

public class MYApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelp.createVolleyHelp(this);
    }
}
