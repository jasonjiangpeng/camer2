package cliu.TutorialOnFaceDetect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/8/11.
 */

public class MyPhotoView extends View {


    public MyPhotoView(Context context) {
        super(context);
    }

    public MyPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private Bitmap bitmap;
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
