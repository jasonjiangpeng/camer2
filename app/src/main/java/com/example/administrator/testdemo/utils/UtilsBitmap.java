package com.example.administrator.testdemo.utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2017/8/17.
 */

public class UtilsBitmap {
    public static byte[] bitmapToBitmap(Bitmap bitmap){
        if (bitmap==null){
            throw new NullPointerException();
        }
       ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[]  bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }
}
