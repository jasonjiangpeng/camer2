package com.example.administrator.testdemo.face;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.administrator.testdemo.bean.FaceMessage;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class BitmapCanvas  {
  private   Bitmap bitmap;
  private List<FaceMessage>  messageList;
    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public BitmapCanvas(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public List<FaceMessage> getMessageList() {
        return messageList;
    }
    public void setMessageList(List<FaceMessage> messageList,CanvasFinish c) {
        this.messageList = messageList;
        draw(c);
    }
    public void draw(CanvasFinish c){
        if (messageList!=null){
            Canvas  canvas =new Canvas(bitmap);
           // canvas.drawRect(getRectF(),getpaint());
            for (int i = 0; i <messageList.size() ; i++) {
                canvas.drawRect(messageList.get(i).getRectF(),getpaint());
                canvas.drawText(messageList.get(i).getGender()+"年龄"+messageList.get(i).getAge(),messageList.get(i).getPoint().x,messageList.get(i).getPoint().y,textPaint());
            }
            c.canvasfinish();
        }

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
