package com.example.administrator.testdemo;

import android.bluetooth.BluetoothAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.example.administrator.testdemo.Log.LogShow;
import com.example.mylibrary.FaceAddUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {
private String tag=this.getClass().getSimpleName();
Handler  han=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        new Thread(){
            @Override
            public void run() {

            }
        }.start();
    }
};
    byte[] bytes=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogShow.logShow("startDete");
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.timg3);
         bytes = FaceAddUtils.bitmapToBytes(bitmap);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        new Thread(){
            @Override
            public void run() {
                if (FaceAddUtils.isHasFace(bitmap,8)){
                    int[] c=FaceAddUtils.faceDetect(bytes);
                    LogShow.logShow(c.length);
                    for (int i = 0; i <c.length ; i++) {
                        LogShow.logShow(c[i]+"=====xxxxxxxxx==========");
                    }
                 //   han.sendEmptyMessage(0);
                }
            }
        }.start();
     //   LogShow.logShow(FaceAddUtils.isHasFace(bitmap,8));
        LogShow.logShow(bytes.length);



    }
  public void onclicka(View view){
      LogShow.logShow(tag,"start");
      BluetoothAdapter  bluetoothAdapter =BluetoothAdapter.getDefaultAdapter();
      if (bluetoothAdapter.isEnabled()){
          bluetoothAdapter.setName("完善");
          LogShow.logShow(tag,"時");
      }else {
          bluetoothAdapter.enable();
          LogShow.logShow(tag,"沒有");
      }

  }

}
