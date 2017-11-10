package com.example.administrator.testdemo.faceadd2;

import android.graphics.Bitmap;

import com.example.administrator.testdemo.Log.LogShow;
import com.megvii.facepp.sdk.Facepp;
import com.megvii.facepp.sdk.jni.NativeFaceppAPI;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/18.
 */

public class FaceTest {
    public static void faceTest(Bitmap bitmap,int imageMode){
        ByteArrayOutputStream  arrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,arrayOutputStream);
        NativeFaceppAPI  na=new NativeFaceppAPI();

  /*  //    Facepp.Face[] detect = facepp.detect(arrayOutputStream.toByteArray(), bitmap.getWidth(), bitmap.getHeight(), imageMode);
        LogShow.logShow(detect.length);
        for (int i = 0; i <detect.length ; i++) {
            LogShow.logShow(detect[i].age);
          //  LogShow.logShow(detect[i].);
        }
*/
    }
}
