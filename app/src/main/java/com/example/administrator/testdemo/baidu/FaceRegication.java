package com.example.administrator.testdemo.baidu;

import com.baidu.aip.face.AipFace;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/10/24.
 */

public class FaceRegication {
    public static final String APP_ID = "10277093";
    public static final String API_KEY = "IeSzH8WMug3m5Db4jxl8FbEe";
    public static final String SECRET_KEY = "OO9aAFAEu0PEW1pp2Q3P4He8NUTNbDXP";

    public static String getFace(byte[] bytes) {
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
      //  client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
    //    client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        // 调用接口
        String path = "test.jpg";
        JSONObject res = client.detect(bytes, new HashMap<String, String>());
      //  System.out.println(res.toString());
        return res.toString();
    }
    public static void faceRecognize(byte[] bytes) {
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 参数为本地图片路径
     //   String imagePath = "picture.jpg";
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "5");
        options.put("face_fields", "age,gender");
        JSONObject response = client.detect(bytes,options);
        System.out.println(response.toString());
/*
        // 参数为本地图片文件二进制数组
        byte[] file = readImageFile(imagePath);    // readImageFile函数仅为示例
        JSONObject response = client.detect(file);
        System.out.println(response.toString());*/
    }
}
