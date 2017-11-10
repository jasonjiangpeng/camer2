/*
package cliu.TutorialOnFaceDetect;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jh.rental.user.R;
import com.jh.rental.user.utils.jason.BaseContext;
import com.jh.rental.user.utils.jason.Logger;
import com.jh.rental.user.utils.jason.SnakebarUtils;

import java.io.File;

public class UploadData_Activity extends MyPicture_Activity implements View.OnClickListener {
    private ImageView mIvPicture;
    private ImageView mIvPicture2;
    private Button comit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploaddata_activity);
        setMyTitel(BaseContext.getResValue(R.string.PerfectInformation));
        initView();

    }



    private void initView() {
        Button btUpload = (Button) findViewById(R.id.bt_upload);
        Button bt_rever = (Button) findViewById(R.id.bt_rever);
        comit = (Button) findViewById(R.id.login_login);
        comit.setOnClickListener(this);
        comit.setVisibility(View.GONE);
        mIvPicture = (ImageView) findViewById(R.id.iv_picture);
        mIvPicture2 = (ImageView) findViewById(R.id.iv_picture2);
      */
/*  new Thread() {
            @Override
            public void run() {
                File file = new File(getFilesDir(), "phoneX.jpg");
                File file2 = new File(getFilesDir(), "phoneY.jpg");
                if (file.exists()) {
                    final Bitmap s = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()), mIvPicture.getLayoutParams().width, mIvPicture.getLayoutParams().height, false);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mIvPicture.setImageBitmap(s);

                        }
                    });

                }
                if (file2.exists()) {
                    final Bitmap s = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()), mIvPicture.getLayoutParams().width, mIvPicture.getLayoutParams().height, false);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            comit.setVisibility(View.VISIBLE);
                            mIvPicture2.setImageBitmap(s);

                        }
                    });

                }
            }
        }.start();*//*

        btUpload.setOnClickListener(this);
        bt_rever.setOnClickListener(this);

    }

    @Override
    public void handleManage(int value) {

    }

    @Override
    public void setImageBitmap(Bitmap photo) {
        Bitmap s = Bitmap.createScaledBitmap(photo, mIvPicture.getWidth(), mIvPicture.getHeight(), false);
        if (getisFirst()) {
            mIvPicture.setImageBitmap(s);
        } else {
            mIvPicture2.setImageBitmap(s);
        }

    }

    @Override
    public void onClick(View v) {
    //    requestPermission(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
        switch (v.getId()) {
            case R.id.bt_upload:
                setFirst(true);
                uploadHeadImage();
                break;
            case R.id.bt_rever:
                setFirst(false);
                uploadHeadImage();
                break;
            case R.id.login_login:
                if (photook()) {

                }
                break;
        }
    }

    */
/*判断图片是否完整*//*

    public boolean photook() {
        File file = new File(getFilesDir(), "phoneX.jpg");
        File file1 = new File(getFilesDir(), "phoneY.jpg");
        if (file.exists() && file1.exists()) {
            return true;
        }
        return false;
    }
}*/
