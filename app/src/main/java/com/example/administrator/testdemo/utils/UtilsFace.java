package com.example.administrator.testdemo.utils;

import android.graphics.Bitmap;

import com.example.administrator.testdemo.Log.LogShow;
import com.example.administrator.testdemo.bean.FaceMessage;
import com.example.administrator.testdemo.camera.FinishCallBack;
import com.megvii.cloud.http.CommonOperate;
import com.megvii.cloud.http.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class UtilsFace {
private final  static    String key = "aRKh7sDhT536YLHORdXOFFBqJOeF5Gzv";//api_key
    private final  static  String secret = "zrfIe28avDUP4z36mEy9vg2csDXszxB8";//api_secret
    public static void faceRequest(Bitmap bitmap,FinishCallBack finishCallBack){
        try {
            CommonOperate commonOperate = new CommonOperate(key, secret, false);
            Response response1 = commonOperate.detectByte(UtilsBitmap.bitmapToBitmap(bitmap), 0, "gender,age");
                   if (getFaceToken(response1,finishCallBack)){

                   }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void faceRequest(byte[] bytes,FinishCallBack finishCallBack){
        try {
            CommonOperate commonOperate = new CommonOperate(key, secret, false);
            Response response1 = commonOperate.detectByte(bytes, 0, "gender,age");
            if (getFaceToken(response1,finishCallBack)){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static byte[] bitmapToBitmap(Bitmap bitmap){
        if (bitmap==null){
            throw new NullPointerException();
        }
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[]  bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }
    private  static boolean getFaceToken(Response response, FinishCallBack finishCallBack) throws JSONException {
        finishCallBack.finishCallBack();
        if(response.getStatus() != 200){
            return false;
        }
        String res = new String(response.getContent());
        jsonParse(res);
        finishCallBack.drawData(jsonParse(res));

        return true;
    }
    private static List<FaceMessage> jsonParse(String value){
        LogShow.logShow(value);
        List<FaceMessage>  facemessages=new ArrayList<>();
        try {
            JSONObject jsonObject =new JSONObject(value);
            JSONArray faces = jsonObject.getJSONArray("faces");
            for (int i = 0; i <faces.length() ; i++) {
                LogShow.logShow(faces.get(i).toString());
                FaceMessage faceonew=new FaceMessage();
                faceonew.setGender( faces.getJSONObject(i).getJSONObject("attributes").getJSONObject("gender").getString("value"));
                faceonew.setAge(faces.getJSONObject(i).getJSONObject("attributes").getJSONObject("age").getInt("value"));
                int  size=faces.getJSONObject(i).getJSONObject("face_rectangle").getInt("width");
                int top=  faces.getJSONObject(i).getJSONObject("face_rectangle").getInt("top");
                int left=  faces.getJSONObject(i).getJSONObject("face_rectangle").getInt("left");
                faceonew.setRectF(size,top,left);
                facemessages.add(faceonew);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return facemessages;
    }
}
