package com.example.administrator.testdemo.camera;

import com.example.administrator.testdemo.bean.FaceMessage;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public interface FinishCallBack  {
    void finishCallBack();
    void drawData(List<FaceMessage> messages);
}
