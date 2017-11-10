package com.example.administrator.testdemo.camera.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.testdemo.Log.LogShow;
import com.example.administrator.testdemo.bean.FaceMessage;
import com.example.administrator.testdemo.face.CanvasFinish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class BitmapCanvas2 extends View {
  //private   Bitmap bitmap;
  private List<FaceMessage>  messageList;

    public BitmapCanvas2(Context context) {
        super(context);
    }

    public BitmapCanvas2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (messageList==null){
            messageList=new ArrayList<>();
        }
    }
    private Paint getPaint(){
        Paint  op=new Paint();
        op.setTextSize(100f);
        op.setColor(Color.RED);
        op.setAntiAlias(true);
        op.setStrokeWidth(5f);
        op.setStyle(Paint.Style.STROKE);
        return op;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if (!hasPerson){
            canvas.drawText("没人",100,100,getPaint());
        }else{
            canvas.drawText("you人",100,100,getPaint());
        }
        for (int i = 0; i <messageList.size() ; i++) {
            canvas.drawRect(messageList.get(i).getRectF(),getpaint());
            canvas.drawText(messageList.get(i).getGender()+"年龄"+messageList.get(i).getAge(),messageList.get(i).getPoint().x,messageList.get(i).getPoint().y,textPaint());
        }
    }
   private boolean hasPerson=false;
    public List<FaceMessage> getMessageList() {
        return messageList;
    }
    public void setMessageList(List<FaceMessage> messageLists) {
        if (messageList==null){
            messageList=new ArrayList<>();
        }
        if (messageList!=null){
            messageList.clear();
        }

        this.messageList.addAll(messageLists);
        LogShow.logShow(messageList.size());
        if (messageList.size()<1){
            hasPerson=false;
        }else {
            hasPerson=true;
        }
        invalidate();
     }


    private Paint getpaint(){
      Paint  paint =new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        return paint;
    }
    private Paint textPaint(){
        Paint  paint =new Paint();
        paint.setColor(Color.BLUE);
         paint.setTextSize(40f);
        return paint;
    }

}
