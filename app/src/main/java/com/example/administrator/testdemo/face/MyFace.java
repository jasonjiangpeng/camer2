package com.example.administrator.testdemo.face;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.FaceDetector;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.example.administrator.testdemo.Log.LogShow;
import com.example.administrator.testdemo.R;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017/8/14.
 */

public class MyFace extends Activity {
    MyFaceView  myFaceView;
    private ImageView img;
    private Bitmap bm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myface);
        myFaceView= (MyFaceView) findViewById(R.id.myFaceView);
        img= (ImageView) findViewById(R.id.img);
        bm=drawableToBitmap(img.getBackground());
        bm=bm.copy(Bitmap.Config.RGB_565,true);
    }
    private final int Count=5;
    public void onclicka(View view){
        if (list==null){
            list=new ArrayList<>();
        }else {
            list.clear();
        }
        new Thread(){
            @Override
            public void run() {
                FaceDetector.Face[] faces=new FaceDetector.Face[Count];
                FaceDetector detector=new FaceDetector(bm.getWidth(),bm.getHeight(),Count);
                int faces1 = detector.findFaces(bm, faces);
                LogShow.logShow(faces1);
                for (int i = 0; i <faces1 ; i++) {
                    FaceDetector.Face face = faces[i];
                    PointF pointF=new PointF();
                    face.getMidPoint(pointF);
                    LogShow.logShow(pointF.x+"Y"+pointF.y);
                    float eyesDistance = face.eyesDistance();
                    LogShow.logShow(eyesDistance);
                    RectF rectF=new RectF();
                    rectF.set(pointF.x - eyesDistance, pointF.y - eyesDistance, pointF.x + eyesDistance, pointF.y + eyesDistance);
                    list.add(rectF);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myFaceView.setRectFs(list);
                        }
                    });
                }
            }
        }.start();

      //  myFaceView.setRectFs(list);
    }
    List<RectF>  list;
    public List<RectF> getListRect(){
        List<RectF> list =new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
         RectF  r=new RectF();
            r.set(i*50+10,i*50+10,i*50+100,i*50+100);
            list.add(r);
        }
       return list;
    }
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
       drawable.draw(canvas);

        return bitmap;

    }
}
