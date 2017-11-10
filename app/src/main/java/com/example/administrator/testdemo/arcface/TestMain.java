package com.example.administrator.testdemo.arcface;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.arcsoft.facedetection.AFD_FSDKEngine;
import com.arcsoft.facedetection.AFD_FSDKError;
import com.arcsoft.facedetection.AFD_FSDKFace;
import com.arcsoft.facerecognition.AFR_FSDKEngine;
import com.arcsoft.facerecognition.AFR_FSDKError;
import com.arcsoft.facerecognition.AFR_FSDKFace;
import com.arcsoft.facerecognition.AFR_FSDKMatching;
import com.example.administrator.testdemo.R;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/23.
 */

public class TestMain extends Activity {
    private final String APPID="CNHHFXxBzAPGt1NHQMCzpuV85LkPhBbHZNiA3L9babL8";
    private  final String FACEDETECTION="2VFuoqBt7qvMSjrfxBYvKQLUiXVGeAvMxyvxAcRxKfGa";
    private  final String FACEREGCTION="2VFuoqBt7qvMSjrfxBYvKQLyN8XtnjtKUm5qVn634gtP";
    private  final String FACETRACE="2VFuoqBt7qvMSjrfxBYvKQLMZ8E3poec1S7MLh7QcebB";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("===========");
        Bitmap bitmap =BitmapFactory.decodeResource(getResources(),R.drawable.timg);

        test(bitmap);
     /*   ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
           YuvImage  yuvImage=new YuvImage(     byteArrayOutputStream.toByteArray(), ImageFormat.NV21,bitmap.getWidth(),bitmap.getHeight(),null);
        ByteArrayOutputStream byteArrayOutputStream1=new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()),100,byteArrayOutputStream1);
        process(byteArrayOutputStream1.toByteArray(),bitmap.getWidth(),bitmap.getHeight());*/




    }

        public void process(byte[] data, int width, int height) {
            AFD_FSDKEngine engine = new AFD_FSDKEngine();
            // 用来存放检测到的人脸信息列表
            List<AFD_FSDKFace> result = new ArrayList<AFD_FSDKFace>();
            //初始化人脸检测引擎，使用时请替换申请的APPID和SDKKEY
            AFD_FSDKError err = engine.AFD_FSDK_InitialFaceEngine(APPID,FACEDETECTION, AFD_FSDKEngine.AFD_OPF_0_HIGHER_EXT, 16, 5);
            Log.d("com.arcsoft", "AFD_FSDK_InitialFaceEngine = " + err.getCode());
            //输入的data数据为NV21格式（如Camera里NV21格式的preview数据），其中height不能为奇数，人脸检测返回结果保存在result。
            err = engine.AFD_FSDK_StillImageFaceDetection(data, width, height, AFD_FSDKEngine.CP_PAF_NV21, result);
            Log.d("com.arcsoft", "AFD_FSDK_StillImageFaceDetection =" + err.getCode());
            Log.d("com.arcsoft", "Face=" + result.size());
            for (AFD_FSDKFace face : result) {
                Log.d("com.arcsoft", "Face:" + face.toString());
            }
            //销毁人脸检测引擎
            err = engine.AFD_FSDK_UninitialFaceEngine();
            Log.d("com.arcsoft", "AFD_FSDK_UninitialFaceEngine =" + err.getCode());
        }
    public void processFR(byte[] data1,  int width, int height) {
        AFR_FSDKEngine engine = new AFR_FSDKEngine();
        //用来存放提取到的人脸信息, face_1是注册的人脸，face_2是要识别的人脸
        AFR_FSDKFace face1 = new AFR_FSDKFace();
        AFR_FSDKFace face2 = new AFR_FSDKFace();
        //初始化人脸识别引擎，使用时请替换申请的APPID 和SDKKEY
        AFR_FSDKError error = engine.AFR_FSDK_InitialEngine(APPID,FACEDETECTION);
        Log.d("com.arcsoft", "AFR_FSDK_InitialEngine = " + error.getCode());
        //输入的data数据为NV21格式（如Camera里NV21格式的preview数据）；人脸坐标一般使用人脸检测返回的Rect传入；人脸角度请按照人脸检测引擎返回的值传入。
        error = engine.AFR_FSDK_ExtractFRFeature(data1, width, height, AFR_FSDKEngine.CP_PAF_NV21, new Rect(210, 178, 478, 446), AFR_FSDKEngine.AFR_FOC_0, face1);
        Log.d("com.arcsoft", "Face=" + face1.getFeatureData()[0]+ "," + face1.getFeatureData()[1] + "," + face1.getFeatureData()[2] + "," + error.getCode());
        error = engine.AFR_FSDK_ExtractFRFeature(data1, width, height, AFR_FSDKEngine.CP_PAF_NV21, new Rect(210, 170, 470, 440), AFR_FSDKEngine.AFR_FOC_0, face2);
        Log.d("com.arcsoft", "Face=" + face2.getFeatureData()[0]+ "," + face2.getFeatureData()[1] + "," + face2.getFeatureData()[2] + "," + error.getCode());
        //score用于存放人脸对比的相似度值
        AFR_FSDKMatching score = new AFR_FSDKMatching();
        error = engine.AFR_FSDK_FacePairMatching(face1, face2, score);
        Log.d("com.arcsoft", "AFR_FSDK_FacePairMatching=" + error.getCode());
        Log.d("com.arcsoft", "Score:" + score.getScore());

        //销毁人脸识别引擎
        error = engine.AFR_FSDK_UninitialEngine();
        Log.d("com.arcsoft", "AFR_FSDK_UninitialEngine : " + error.getCode());

    }
    public  void test(Bitmap bitmap){
        System.out.println(System.currentTimeMillis());
        try {
            int  w=bitmap.getWidth();
            int  h=bitmap.getHeight();
            byte[] nv21 = FaceUtils.getNV21(bitmap.getWidth(), bitmap.getHeight(), bitmap);
            System.out.println(System.currentTimeMillis());
            process(nv21,w,h);
            System.out.println(System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
