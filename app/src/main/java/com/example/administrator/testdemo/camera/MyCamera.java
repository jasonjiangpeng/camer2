package com.example.administrator.testdemo.camera;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.FaceDetector;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.testdemo.Log.LogShow;
import com.example.administrator.testdemo.R;
import com.example.administrator.testdemo.camera.view.MyView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;


@EActivity(R.layout.cameramain)
public class MyCamera extends Activity {
@ViewById(R.id.myview)
  public    MyView  myView;
    @ViewById(R.id.img)
    ImageView img;
    public static Bitmap getBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        // Draw background


        Drawable bgDrawable = v.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(c);
        else
            c.drawColor(Color.WHITE);
        // Draw view to canvas
        v.draw(c);
        return b;
    }
    @AfterViews
    public void afterView() {
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
             //   drawRect();
                initCamera2();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                  /*      while (isRun){
                            LogShow.logShow("startDetestion");
                      //      surfaceView.setDrawingCacheEnabled(true);
                    //   getWindow().getDecorView().getDrawingCache()
                            myView.drawBitmap(getBitmapFromView(surfaceView));
                         //   myView.startDetestion(getBitmapFromView(surfaceView));
                      //      surfaceView.buildDrawingCache();
              //      myView.drawBitmap(getBitmapFromView(getWindow().getDecorView()));
                            SystemClock.sleep(2000);
                        }
*/            while (isRun){
                            final View view = getWindow().getDecorView();
                            view.setDrawingCacheEnabled(true);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.buildDrawingCache();
                                    img.setImageBitmap(view.getDrawingCache());
                                }
                            });
                            SystemClock.sleep(500);
                            }


                    }
                }).start();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                isRun=false;
            }
        });

    }
private Handler  childHandle;
private Handler  mainHanlde;
    private ImageReader imagereader;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRun=false;
    }

    private void initCamera2() {
        HandlerThread han=new HandlerThread("Camera2");
        han.start();
         childHandle =new Handler(han.getLooper());
        mainHanlde=new Handler(getMainLooper());
        imagereader=ImageReader.newInstance(1080,1920, ImageFormat.JPEG,1);
        imagereader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                LogShow.logShow("====================================");
         //  LogShow.logShow(reader.getHeight());


            }
        },mainHanlde);
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            cameraManager.openCamera(cameraIdList[0], stateCallback,mainHanlde);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @ViewById(R.id.surfaceView)
    SurfaceView  surfaceView;

private CameraDevice mCameraDevice;
    CameraDevice.StateCallback  stateCallback =new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            LogShow.logShow("onOpened");
            mCameraDevice=camera;

           takePreview();

        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            LogShow.logShow("onDisconnected");
             if (mCameraDevice!=null){
                 mCameraDevice.close();
                 mCameraDevice=null;
             }
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
           LogShow.logShow("error");
        }
    };



     CameraCaptureSession mCameraCaptureSession ;
private void takePreview(){
    try {
        final CaptureRequest.Builder capture=mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
        capture.addTarget(surfaceView.getHolder().getSurface());

         mCameraDevice.createCaptureSession(Arrays.asList(surfaceView.getHolder().getSurface(), imagereader.getSurface()), new CameraCaptureSession.StateCallback() {
             @Override
             public void onConfigured(@NonNull CameraCaptureSession session) {
              if (mCameraDevice==null){
                  return;
              }
                 mCameraCaptureSession=session;

                 try {
                     capture.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                     // 打开闪光灯
                     capture.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                     // 显示预览
                     CaptureRequest previewRequest = capture.build();
                     mCameraCaptureSession.setRepeatingRequest(previewRequest, null, childHandle);
                 } catch (CameraAccessException e) {
                     e.printStackTrace();
                 }


             }

             @Override
             public void onConfigureFailed(@NonNull CameraCaptureSession session) {

             }
         },childHandle);
    } catch (CameraAccessException e) {
        e.printStackTrace();
    }
}
private boolean  isRun=true;
private final int   sleepTime=50;
  private void drawRect(){
      while (isRun){
          Canvas canvas = surfaceView.getHolder().lockCanvas();
          synchronized (surfaceView.getHolder()){
                canvas.drawText("我傻傻哒",100,100,getPaint());
          }
          surfaceView.getHolder().unlockCanvasAndPost(canvas);
          SystemClock.sleep(sleepTime);
      }
  }
   private Paint  getPaint(){
       Paint  op=new Paint();
       op.setTextSize(100f);
       op.setColor(Color.RED);
       op.setAntiAlias(true);
       return op;
   }
}

