package bwf.androiddemos;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import bwf.androiddemos.utils.DisplayUtil;

/**
 * Created by Lizhangfeng on 2016/8/16 0016.
 * Description: 自定义Applications
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    public static int statusHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        statusHeight = DisplayUtil.getStatusBarHeight(this);
        Fresco.initialize(this);
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }

}
