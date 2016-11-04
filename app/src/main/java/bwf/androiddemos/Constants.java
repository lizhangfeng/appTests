package bwf.androiddemos;

import android.os.Environment;

import java.io.File;

/**
 * Created by lizhangfeng on 16/3/29.
 * <p>
 * description: 常量类
 */
public class Constants {


    /**
     * 时区，日历写入的时候需要
     */
    public static final String TIME_ASIA = "Asia/Shanghai";
    /**
     * 提前多少分钟提醒，日历写入的时候需要
     */
    public static final int MINUTES = 1440;//默认提前一天

    /* 图片保存路径 */
    public static final String PHOTO_URI = "/BWF/DEMOS/Image";

    /**
     * 用来标识请求照相功能的activity
     */
    public static final int CAMERA_WITH_DATA = 3021;
    /**
     * 用来标识请求相册的activity
     */
    public static final int PHOTO_PICKED_WITH_DATA = 3022;
    /**
     * 用跳转剪切
     */
    public static final int CROP_BIG_PICTURE = 3024;
    /**
     * 拍照的照片存储位置
     */
    public static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + Constants.PHOTO_URI);

    public static final String APP_CITY_FILE_NAME = "CityJson.json";

}
