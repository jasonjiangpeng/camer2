package com.example.administrator.testdemo.face;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.testdemo.Log.LogShow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/14.
 */

public class VolleyHelp {
    private static VolleyHelp volleyHelp;
    private static RequestQueue requestQueue;
    private VolleyHelp(Context context) {
          this.requestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyHelp createVolleyHelp(Context context){
        if (volleyHelp==null){
            synchronized (VolleyHelp.class){
                if (volleyHelp==null){
                    volleyHelp=new VolleyHelp(context);
                }
            }
        }
        return volleyHelp;
    }

    private static String faceUrl="https://api-cn.faceplusplus.com/humanbodypp/beta/detect";
    public static void vooleyPost(){
        StringRequest  stringRequest =new StringRequest(Request.Method.POST, faceUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogShow.logShow("response"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogShow.logShow("error");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (getMap()!=null){
                    return getMap();
                }
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }
    private static Map<String, String> getMap(){
        Map<String, String>  map=new HashMap<>();
        map.put("api_key",key);
        map.put("api_secret",secret);
        map.put("image_url",imageUrl);
        map.put("return_attributes","gender,cloth_color");

        return map;

    }
    private static Map<String, String> getMap2(){
        Map<String, String>  map=new HashMap<>();
        map.put("api_key",key);
        map.put("api_secret",secret);
        map.put("image_url",imageUrl);
        map.put("return_landmark","1");
        map.put("return_attributes","gender,age");
        return map;

    }
    static   String key = "aRKh7sDhT536YLHORdXOFFBqJOeF5Gzv";//api_key
    static  String secret = "zrfIe28avDUP4z36mEy9vg2csDXszxB8";//api_secret
    static String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502792632351&di=34eef52424b973109545cf9e1e0c0d28&imgtype=0&src=http%3A%2F%2Fimg.juimg.com%2Ftuku%2Fyulantu%2F131225%2F328186-1312250QU252.jpg";
private static String faceUrl2="https://api-cn.faceplusplus.com/facepp/v3/detect";
    public static void vooleyPost2(){
        StringRequest  stringRequest =new StringRequest(Request.Method.POST, faceUrl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject  object =new JSONObject(response);
                    JSONArray faces = object.getJSONArray("faces");
                    LogShow.logShow(faces.length());
                    for (int i = 0; i <faces.length() ; i++) {
                    //    LogShow.logShow(faces.get(i).toString());
                        JSONObject ageb =new JSONObject(faces.get(i).toString());
                        String gender = ageb.getJSONObject("attributes").getJSONObject("gender").getString("value");
                        String age = ageb.getJSONObject("attributes").getJSONObject("age").getString("value");
                        int width = ageb.getJSONObject("face_rectangle").getInt("width");
                        int top = ageb.getJSONObject("face_rectangle").getInt("top");
                        int left = ageb.getJSONObject("face_rectangle").getInt("left");
                        int height = ageb.getJSONObject("face_rectangle").getInt("height");
                        LogShow.logShow(gender);
                        LogShow.logShow(age);
                        LogShow.logShow(width+"top"+top+"left"+left+"heigh"+height);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                LogShow.logShow("error"+error.networkResponse.statusCode);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (getMap2()!=null){
                    return getMap2();
                }
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }
    public static void vooleyPost2(final Map<String,String> map){
        final long start=System.currentTimeMillis();

        StringRequest  stringRequest =new StringRequest(Request.Method.POST, faceUrl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject  object =new JSONObject(response);
                    JSONArray faces = object.getJSONArray("faces");
                    LogShow.logShow(faces.length());
                    for (int i = 0; i <faces.length() ; i++) {
                        //    LogShow.logShow(faces.get(i).toString());
                        JSONObject ageb =new JSONObject(faces.get(i).toString());
                        String gender = ageb.getJSONObject("attributes").getJSONObject("gender").getString("value");
                        String age = ageb.getJSONObject("attributes").getJSONObject("age").getString("value");
                        int width = ageb.getJSONObject("face_rectangle").getInt("width");
                        int top = ageb.getJSONObject("face_rectangle").getInt("top");
                        int left = ageb.getJSONObject("face_rectangle").getInt("left");
                        int height = ageb.getJSONObject("face_rectangle").getInt("height");
                        LogShow.logShow(gender);
                        LogShow.logShow(age);
                        LogShow.logShow(width+"top"+top+"left"+left+"heigh"+height);
                    }
                    long end=System.currentTimeMillis()-start;
                    LogShow.logShow(MainActivity.class,end);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                LogShow.logShow("error"+error.networkResponse.statusCode);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (map!=null){
                    return map;
                }
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }

}
