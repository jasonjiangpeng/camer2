/*
package com.example.administrator.testdemo.handle;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;

import com.example.administrator.testdemo.Log.LogShow;
import com.example.administrator.testdemo.R;
import com.example.administrator.testdemo.ndk.MyNdk;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

*/
/**
 * Created by Administrator on 2017/8/14.
 *//*

@EActivity(R.layout.activity_main)
public class TestHandleThread extends Activity {
    private HandlerThread handlerThread;
    private Handler handler;

    @AfterViews
    public void afterView(){

        handlerThread=new HandlerThread("myHandlerThre");
        handlerThread.start();
        handler=new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                LogShow.logShow(Thread.currentThread().getName());
            //    textView.setText("ssddd");
            }
        };
    }
    @Click(R.id.btn_select)
    public void sendHand(){
        handler.sendEmptyMessage(0);
    }
    @ViewById(R.id.TextView)
    public TextView textView;
}
*/
