package com.example.administrator.testdemo.baidu;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.testdemo.R;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */

public class MainActivity extends Activity   {
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void onClick(View view){
        System.out.println("start");
        new Thread(){
            @Override
            public void run() {

                System.out.println("=================="+System.currentTimeMillis());
                FaceRegication.faceRecognize(getbytes(R.drawable.timg4));
                System.out.println("=================="+System.currentTimeMillis());
            }
        }.start();

    }
    public byte[] getbytes(int bitId){
        Bitmap  bitmap = BitmapFactory.decodeResource(getResources(),bitId );
        ByteArrayOutputStream fileOutputStream0i=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,fileOutputStream0i);
        return fileOutputStream0i.toByteArray();
    }
}
