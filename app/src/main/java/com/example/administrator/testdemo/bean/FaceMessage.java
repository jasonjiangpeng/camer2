package com.example.administrator.testdemo.bean;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by Administrator on 2017/8/15.
 */

public class FaceMessage {
   private String gender;
    private int age;
    private RectF rectF;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender=gender.equals("Male")?"男":"女";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RectF getRectF() {
        return rectF;
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
    }
    public void setRectF(int size,int top,int left) {
       this.rectF=new RectF(left,top,left+size,top+size);
    }


    public PointF  getPoint(){
        if (rectF!=null){
            PointF  point =new PointF(rectF.left,rectF.top);
            return point;
        }
        return null;
    }
}
