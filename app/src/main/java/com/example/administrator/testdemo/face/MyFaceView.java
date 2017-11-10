package com.example.administrator.testdemo.face;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class MyFaceView extends View {
    private List<RectF> rectFs;

    public MyFaceView(Context context) {
        super(context);
    }

    public MyFaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (rectFs!=null&&rectFs.size()>0){
            drawFace( canvas);
        }

    }
   private Paint getPain(){
       Paint  paint = new Paint();
       paint.setColor(Color.RED);
       paint.setStrokeWidth(2);
       paint.setStyle(Paint.Style.STROKE);
       return paint;
   }
    private void drawFace(Canvas canvas) {
        for (int i = 0; i <rectFs.size() ; i++) {
            canvas.drawRect(rectFs.get(i),getPain());
        }

    }
    public void setRectFs( List<RectF> rectFs){
        this.rectFs=rectFs;
            invalidate();
    }

}
