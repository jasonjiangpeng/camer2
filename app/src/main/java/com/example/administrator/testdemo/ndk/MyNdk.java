package com.example.administrator.testdemo.ndk;

/**
 * Created by Administrator on 2017/8/11.
 */

public class MyNdk  {
    static {
        System.loadLibrary("myndk");
    }
    public native String  getName();
}
