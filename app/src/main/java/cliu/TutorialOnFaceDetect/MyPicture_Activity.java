/*
package cliu.TutorialOnFaceDetect;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jh.rental.user.R;
import com.jh.rental.user.api.ApiGet;
import com.jh.rental.user.bean.home.GetToken;
import com.jh.rental.user.model.HttpVolley;
import com.jh.rental.user.presenter.net.NetSucceed;
import com.jh.rental.user.presenter.qiniu.QnUpDown;
import com.jh.rental.user.utils.jason.AppUtils;
import com.jh.rental.user.utils.jason.Logger;
import com.jh.rental.user.utils.jason.SnakebarUtils;
import com.jh.rental.user.view.BaseApplication;
import com.jh.rental.user.view.actitity.TitelBarAcitvity;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

*/
/**
 * Created by 骏辉出行 on 2017/5/18.
 *//*


public class MyPicture_Activity extends TitelBarAcitvity {
    private static final int PHOTO_REQUEST_CAREMA = 85;
    private static final int PHOTO_REQUEST_GALLERY = 86;
    private static final int PHOTO_REQUEST_CUT = 87;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
  //  private File tempFile;

    private boolean  isFirst=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent,null));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });
        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                getImageFromCamera();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image*/
/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
    */
/*请求拍照*//*

    protected void getImageFromCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {

     if (AppUtils.isHasPermission(Manifest.permission.CAMERA)){
         Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         startActivityForResult(getImageByCamera, PHOTO_REQUEST_CAREMA);
     }else {
         SnakebarUtils.showToast("没有权限");
         requestPermission(new String[]{Manifest.permission.CAMERA},201);
     //    ActivityCompat.requestPermissions(BaseApplication.currentActivity(),new String[]{"android.media.action.IMAGE_CAPTURE"},201);
     }
        }
        else {
            Toast.makeText(getApplicationContext(),"请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==201){

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                gotoClipActivity(uri);
            }
        }
        if (requestCode == PHOTO_REQUEST_CAREMA) {
            Uri uri = data.getData();
            if (uri == null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    final Bitmap photo = (Bitmap) bundle.get("data"); //get bitmap
                    setImageBitmap(photo);
                    HttpVolley.getInstance().getRequestData(ApiGet.getToken,new NetSucceed<>(GetToken.class, new NetSucceed.HolderData<GetToken>() {
                        @Override
                        public void holdData(GetToken bean) {
                            QnUpDown.updataVisa(BaseApplication.currentActivity(),bean.getToken(),photo,"myphoto.jpg",bean.getDomainUrl());
                        }
                    }));
                } else {
                    Toast.makeText(getApplicationContext(), "异常错误", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        }
            if (requestCode == PHOTO_REQUEST_CUT) {
                Logger.soutMessage("===================");
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras!=null){
                        Logger.soutMessage("setmosadad");
                        final Bitmap bitmap = extras.getParcelable("data");
                        setImageBitmap(bitmap);
                        HttpVolley.getInstance().getRequestData(ApiGet.getToken,new NetSucceed<>(GetToken.class, new NetSucceed.HolderData<GetToken>() {
                            @Override
                            public void holdData(GetToken bean) {
                                QnUpDown.updataVisa(BaseApplication.currentActivity(),bean.getToken(),bitmap,"myphoto.jpg",bean.getDomainUrl());
                            }
                        }));

                    }

                }
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean getisFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public  boolean saveImage(Bitmap photo) {
            FileOutputStream outputStream;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, out);
            try {
                out.flush();
              if (getisFirst()){
                  outputStream = openFileOutput("phoneX.jpg", Context.MODE_PRIVATE);
              }else {
                  outputStream = openFileOutput("phoneY.jpg", Context.MODE_PRIVATE);
              }
                out.writeTo(outputStream);
                out.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

    private void gotoClipActivity(Uri uri) {
        Logger.soutMessage("==============xxxxxxxxxxxxxxxxxx");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image*/
/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 200);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
}
    public void setImageBitmap(Bitmap  photo){
    }
}
*/
