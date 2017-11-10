package com.example.administrator.testdemo.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.testdemo.R;

/**
 * Created by Administrator on 2017/8/16.
 */

public class Camera2Activity extends AppCompatActivity  {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameramain2);
    }
    public void onClicka(View view){
        Intent intent=new Intent(this,MyTextureView.class);
        startActivity(intent);
    }
    public void onClickb(View view){
        Intent intent=new Intent(this,MyTextureView2.class);
        startActivity(intent);
    }
}
