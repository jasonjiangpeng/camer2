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

/**
 * Created by Administrator on 2017/8/17.
 */

public class MyView2 extends View {
    private  int count=5;
    public MyView2(Context context) {
        super(context);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        finishCallBack.finishCallBack();
        if (bitmaps!=null){
            canvas.drawBitmap(bitmaps,100,100,null);
            return;
        }
        for (int i = 0; i < rects.size(); i++) {
            canvas.drawRect(rects.get(i),getPaint());
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
    private List<RectF>  rects=new ArrayList<>();
    private Bitmap  bitmaps;
    public void drawBitmap(Bitmap bitmap){
        this.bitmaps=bitmap;
      //     postInvalidate();
    }
    public void startDetestion(Bitmap bitmap) {
         new FindFaceTask(bitmap).execute();
    }
  private   FinishCallBack  finishCallBack;

    public FinishCallBack getFinishCallBack() {
        return finishCallBack;
    }

    public void setFinishCallBack(FinishCallBack finishCallBack) {
        this.finishCallBack = finishCallBack;
    }

    class FindFaceTask extends AsyncTask<Void,Void,FaceDetector.Face[]>{
        private Bitmap bitmap;
        private  int face1;
        public FindFaceTask(Bitmap bitmap) {
            this.bitmap=bitmap;
        }
        @Override
        protected FaceDetector.Face[] doInBackground(Void... params) {
            FaceDetector.Face[]  faces=new FaceDetector.Face[count];
            FaceDetector faceDetector =new FaceDetector(bitmap.getWidth(),bitmap.getHeight(),count);
            face1 = faceDetector.findFaces(bitmap, faces);
            LogShow.logShow("face1"+face1);
            return faces;
        }

        @Override
        protected void onPostExecute(FaceDetector.Face[] faces) {
            LogShow.logShow("onPostExecute");
            for (int i = 0; i <face1 ; i++) {
                FaceDetector.Face  fces=faces[i];
                if (faces!=null){
                    PointF pointF=new PointF();
                    fces.getMidPoint(pointF);
                    float    eyesDistance=fces.confidence();
                    RectF re=new RectF(pointF.x - eyesDistance, pointF.y - eyesDistance, pointF.x + eyesDistance, pointF.y + eyesDistance);
                    rects.add(re);
                }
            }
            postInvalidate();
        }
    }
}
