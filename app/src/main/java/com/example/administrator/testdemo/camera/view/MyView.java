package com.example.administrator.testdemo.camera.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.media.FaceDetector;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.testdemo.Log.LogShow;
import com.example.administrator.testdemo.camera.FinishCallBack;

import java.util.ArrayList;
import java.util.List;

import cliu.TutorialOnFaceDetect.Logger;

/**
 * Created by Administrator on 2017/8/17.
 */

public class MyView extends View {
    private final int count=10;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        finishCallBack.finishCallBack();
   if (bitmap!=null){
       canvas.drawBitmap(bitmap,null,new RectF(100,100,200,200),null);
   }
   if (face1==0&&isDraw==true){
       canvas.drawText("没人",100,100,getPaint());
   }
        for (int i = 0; i < rects.size(); i++) {
            canvas.drawRect(rects.get(i),getPaint());
        }
    }
    private boolean isDraw=true;

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
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
    private List<RectF>  rects=new ArrayList<>();


    public void startDetestion(Bitmap bitmaps) {
        this.bitmap=bitmaps.copy(Bitmap.Config.RGB_565,true);
        post(new Runnable() {
            @Override
            public void run() {
                new FindFaceTask().execute();
            }
        });

    }
  private   FinishCallBack  finishCallBack;

    public FinishCallBack getFinishCallBack() {
        return finishCallBack;
    }

    public void setFinishCallBack(FinishCallBack finishCallBack) {
        this.finishCallBack = finishCallBack;
    }
    private  int face1;
    private Bitmap bitmap;
    class FindFaceTask extends AsyncTask<Void,Void,FaceDetector.Face[]>{


        public FindFaceTask() {

        }
        @Override
        protected FaceDetector.Face[] doInBackground(Void... params) {
        LogShow.logShow("bum"+bitmap.getWidth()+"h"+bitmap.getHeight());
            FaceDetector faceDetector =new FaceDetector(bitmap.getWidth(),bitmap.getHeight(),count);
            FaceDetector.Face[]  faces=new FaceDetector.Face[count];
            face1 = faceDetector.findFaces(bitmap, faces);
            LogShow.logShow("face1"+face1);

            return faces;
        }

        @Override
        protected void onPostExecute(FaceDetector.Face[] faces) {
            LogShow.logShow("onPostExecute");
            rects.clear();
            for (int i = 0; i <face1 ; i++) {
                FaceDetector.Face  fces=faces[i];
                if (faces!=null){
                    PointF pointF=new PointF();
                    fces.getMidPoint(pointF);
                    float    eyesDistance=fces.eyesDistance();
                    LogShow.logShow(pointF.x+"y"+pointF.y+"eyesDistance"+eyesDistance);
                    RectF re=new RectF(pointF.x - eyesDistance, pointF.y - eyesDistance, pointF.x + eyesDistance, pointF.y + eyesDistance);
                    rects.add(re);
                }
            }
            postInvalidate();
        }
    }
}
