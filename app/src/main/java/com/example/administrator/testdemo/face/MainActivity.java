package com.example.administrator.testdemo.face;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.testdemo.Log.LogShow;
import com.example.administrator.testdemo.R;
import com.example.administrator.testdemo.bean.FaceMessage;
import com.megvii.cloud.http.CommonOperate;
import com.megvii.cloud.http.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  /* private    String key = "yourKey";//api_key
    private String secret = "youreApi_secret";//api_secret*/
       String key = "aRKh7sDhT536YLHORdXOFFBqJOeF5Gzv";//api_key
      String secret = "zrfIe28avDUP4z36mEy9vg2csDXszxB8";//api_secret
    long start=System.currentTimeMillis();
    private ImageView img;
    private Bitmap bitmap;
    private BitmapCanvas bitmacanvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);
        img= (ImageView) findViewById(R.id.img);
        bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.timg3).copy(Bitmap.Config.RGB_565,true);
        bitmacanvas=new BitmapCanvas(bitmap);
        img.setImageBitmap(bitmap);
        new Thread(new Runnable() {
            @Override
            public void run() {
                post();
            }
        }).start();
    }
    public void post(){
       ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        bytes = byteArrayOutputStream.toByteArray();
        if (bytes.length>0){
            start=System.currentTimeMillis();
            handler.sendEmptyMessage(0);
        }
    }

    private byte[] bytes;
    private Handler handler =new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==2){
                img.setImageBitmap(bitmap);
                return;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //  ArrayList<String> faces = new ArrayList<>();
                    CommonOperate commonOperate = new CommonOperate(key, secret, false);
                    try {
                        Response response1 = commonOperate.detectByte(bytes, 0, "gender,age");
                      if (getFaceToken(response1)){
                         bitmacanvas.setMessageList(facemessages, new CanvasFinish() {
                             @Override
                             public void canvasfinish() {
                                 handler.sendEmptyMessage(2);
                             }
                         });
                      }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    };

    private boolean getFaceToken(Response response) throws JSONException {
        if(response.getStatus() != 200){
            return false;
        }
        String res = new String(response.getContent());
         jsonParse(res);
        return true;
    }
    private List<FaceMessage> facemessages;
    private void jsonParse(String value){
        if (facemessages==null){
            facemessages=new ArrayList<>();
        }else {
            facemessages.clear();
        }
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
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap!=null){
            bitmap.recycle();
        }
        if (facemessages!=null){
            facemessages.clear();
        }
        if (handler!=null){
           handler.removeCallbacksAndMessages(null);
        }
    }
}
