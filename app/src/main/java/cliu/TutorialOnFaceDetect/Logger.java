package cliu.TutorialOnFaceDetect;

import android.media.FaceDetector;
import android.media.MediaCodec;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;

import java.nio.Buffer;

/**
 * Created by Administrator on 2017/8/14.
 */

public class Logger {
    public static void logV(Class c,Object o){
        if (o==null){


            return;
        }
        Log.v(c.getSimpleName(),o.toString());
    }
    public static void logD(Class c,Object o){
        if (o==null){
            return;
        }
        Log.d(c.getSimpleName(),o.toString());
    }
    public static void logE(Class c,Object o){
        if (o==null){
            return;
        }
        Log.e(c.getSimpleName(),o.toString());
    }
    public static void logW(Class c,Object o){
        if (o==null){
            return;
        }
        Log.w(c.getSimpleName(),o.toString());
    }
    public static void logI(Class c,Object o){
        if (o==null){
            return;
        }
        Log.i(c.getSimpleName(),o.toString());
    }
}
