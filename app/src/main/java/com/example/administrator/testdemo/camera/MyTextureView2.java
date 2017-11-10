package com.example.administrator.testdemo.camera;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import com.example.administrator.testdemo.Log.LogShow;
import com.example.administrator.testdemo.R;
import com.example.administrator.testdemo.bean.FaceMessage;
import com.example.administrator.testdemo.camera.view.BitmapCanvas2;
import com.example.administrator.testdemo.camera.view.MyView;
import com.example.administrator.testdemo.utils.UtilsFace;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class MyTextureView2 extends Activity implements TextureView.SurfaceTextureListener {
    private TextureView mPreviewView;
    private HandlerThread mThreadHandler;
    private Handler mHandler,childHandler;
   private MyView myviews;
    private BitmapCanvas2 myView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textureview);
        myviews= (MyView) findViewById(R.id.myviews);
        myviews.setDraw(false);
        myviews.setFinishCallBack(new FinishCallBack() {
            @Override
            public void finishCallBack() {
                isRun=true;
            }

            @Override
            public void drawData(List<FaceMessage> messages) {

            }
        });
        mThreadHandler = new HandlerThread("CAMERA2");
        mThreadHandler.start();
        mHandler = new Handler(Looper.getMainLooper());
        childHandler = new Handler(mThreadHandler.getLooper());
        mPreviewView = (TextureView) findViewById(R.id.textureview);
        myView = (BitmapCanvas2) findViewById(R.id.myview);

        mPreviewView.setSurfaceTextureListener(this);

    }

public void onClick(View view){
        takePickture();
    }


    CameraManager cameraManager;
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        LogShow.logShow("========surface===========");
         cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            String[] CameraIdList = cameraManager.getCameraIdList();
            //获取可用相机设备列表
            CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(CameraIdList[0]);
            //在这里可以通过CameraCharacteristics设置相机的功能,当然必须检查是否支持
            characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            //就像这样
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            if (isFirst){
                cameraManager.openCamera(CameraIdList[1], mCameraDeviceStateCallback, mHandler);
            }else {
                cameraManager.openCamera(CameraIdList[1], mCameraDeviceStateCallback, mHandler);
            }


        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
  private boolean isFirst=true;
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
    private CameraDevice.StateCallback mCameraDeviceStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            cameras=camera;
            LogShow.logShow("onOpened");
            try {
                startPreview(camera);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnected(CameraDevice camera) {

        }

        @Override
        public void onError(CameraDevice camera, int error) {

        }
    };
    ImageReader  imageReader;
    CaptureRequest.Builder mPreviewBuilder;
    private void startPreview(CameraDevice camera) throws CameraAccessException {
        SurfaceTexture texture = mPreviewView.getSurfaceTexture();
        texture.setDefaultBufferSize(mPreviewView.getWidth(), mPreviewView.getHeight());
        Surface surface = new Surface(texture);
        try {
          mPreviewBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        mPreviewBuilder.addTarget(surface);

        imageReader=ImageReader.newInstance(mPreviewView.getWidth(),mPreviewView.getHeight(), ImageFormat.JPEG,10);

        imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
          LogShow.logShow("onImageAvailable");
            }
        },mHandler);
        camera.createCaptureSession(Arrays.asList(surface,imageReader.getSurface()), mSessionStateCallback, mHandler);
    }
    private CameraCaptureSession cameraCaptureSession;
    private CameraCaptureSession.StateCallback mSessionStateCallback = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(CameraCaptureSession session) {
            cameraCaptureSession=session;
            try {
                //session.capture(mPreviewBuilder.build(), mSessionCaptureCallback, mHandler);
                session.setRepeatingRequest(mPreviewBuilder.build(), mSessionCaptureCallback, mHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConfigureFailed(CameraCaptureSession session) {

        }
    };
     CaptureRequest.Builder captureRequestBuilder;
    private boolean  isRun=true;
    private CameraCaptureSession.CaptureCallback mSessionCaptureCallback =
            new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
               if (isRun){
                   isRun=false;
                   new Thread(new Runnable() {
                       @Override
                       public void run() {

                           byte[] value=UtilsFace.bitmapToBitmap(mPreviewView.getBitmap());
                           if (value.length>0){
                               UtilsFace.faceRequest(value, new FinishCallBack() {
                                   @Override
                                   public void finishCallBack() {
                                       isRun=true;
                                   }

                                   @Override
                                   public void drawData(List<FaceMessage> messages) {
                                     myView.setMessageList(messages);
                                   }


                               });
                           }else {
                               isRun=true;
                           }
                       }
                   }).start();

               }



                }

                @Override
                public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request,
                                                CaptureResult partialResult){
                    LogShow.logShow("onCaptureProgressed");
                }
    };
private CameraDevice cameras;
   private void takePickture(){
       try {
           captureRequestBuilder=cameras.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
           captureRequestBuilder.addTarget(imageReader.getSurface());
           captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
           captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
           CaptureRequest mCaptureRequest = captureRequestBuilder.build();
           cameraCaptureSession.capture(mCaptureRequest, null,childHandler);
       } catch (CameraAccessException e) {
           e.printStackTrace();
       }

   }

}
