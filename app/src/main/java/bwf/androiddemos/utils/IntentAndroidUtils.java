package bwf.androiddemos.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import static android.provider.Settings.ACTION_SETTINGS;

/**
 * Created by Lizhangfeng on 2016/10/24 0024.
 * Description: 跳转到系统各个界面utils
 */

public class IntentAndroidUtils {

    /**
     * 跳转系统相册
     */
    public static void intentToGallery(Context context) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        context.startActivity(intent);
    }

    /**
     * 跳转系统相机
     */
    public static void intentToCamera(Context context) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        intent.putExtra("outputFormat",
                Bitmap.CompressFormat.JPEG.toString());
        context.startActivity(intent);
    }

    /**
     * 跳转系统播放器
     *
     * @param context
     */
    public static void intentToVideo(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(Environment.getExternalStorageDirectory() + "/test.mp4"), "video/mp4");
        context.startActivity(intent);
    }

    /**
     * 跳转系统设置页面
     *
     * @param context
     */
    public static void intentToSettings(Context context) {
        Intent intent =  new Intent(ACTION_SETTINGS);
        context.startActivity(intent);

        //设置其他页面的跳转方式如下：
//        android.provider.Settings：
//        ACTION_ACCESSIBILITY_SETTINGS ：    // 跳转系统的辅助功能界面
//        Intent intent =  new Intent(ACTION_ACCESSIBILITY_SETTINGS);
//        startActivity(intent);
//
//        ACTION_ADD_ACCOUNT ：               // 显示添加帐户创建一个新的帐户屏幕。【测试跳转到微信登录界面】
//        ACTION_AIRPLANE_MODE_SETTINGS：       // 飞行模式，无线网和网络设置界面
//        ACTION_WIRELESS_SETTINGS  ：
//        ACTION_APN_SETTINGS：                 //  跳转 APN设置界面
//        ACTION_APPLICATION_DETAILS_SETTINGS：   // 根据包名跳转到系统自带的应用程序信息界面
//        ACTION_APPLICATION_DEVELOPMENT_SETTINGS :  // 跳转开发人员选项界面
//        ACTION_APPLICATION_SETTINGS ：      // 跳转应用程序列表界面
//        ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS   // 跳转到应用程序界面【所有的】
//        ACTION_MANAGE_APPLICATIONS_SETTINGS  ：//  跳转 应用程序列表界面【已安装的】
//        ACTION_BLUETOOTH_SETTINGS  ：      // 跳转系统的蓝牙设置界面
//        ACTION_DATA_ROAMING_SETTINGS ：   //  跳转到移动网络设置界面
//        ACTION_DATE_SETTINGS ：           //  跳转日期时间设置界面
//        ACTION_DEVICE_INFO_SETTINGS  ：    // 跳转手机状态界面
//        ACTION_DISPLAY_SETTINGS  ：        // 跳转手机显示界面
//        ACTION_DREAM_SETTINGS     【API 18及以上 没测试】
//        ACTION_INPUT_METHOD_SETTINGS ：    // 跳转语言和输入设备
//        ACTION_INPUT_METHOD_SUBTYPE_SETTINGS  【API 11及以上】  //  跳转 语言选择界面 【多国语言选择】
//        ACTION_INTERNAL_STORAGE_SETTINGS         // 跳转存储设置界面【内部存储】
//        ACTION_MEMORY_CARD_SETTINGS    ：   // 跳转 存储设置 【记忆卡存储】
//        ACTION_LOCALE_SETTINGS  ：         // 跳转语言选择界面【仅有English 和 中文两种选择】
//        ACTION_LOCATION_SOURCE_SETTINGS :    //  跳转位置服务界面【管理已安装的应用程序。】
//        ACTION_NETWORK_OPERATOR_SETTINGS ： // 跳转到 显示设置选择网络运营商。
//        ACTION_NFCSHARING_SETTINGS  ：       // 显示NFC共享设置。 【API 14及以上】
//        ACTION_NFC_SETTINGS  ：           // 显示NFC设置。这显示了用户界面,允许NFC打开或关闭。  【API 16及以上】
//        ACTION_PRIVACY_SETTINGS ：       //  跳转到备份和重置界面
//        ACTION_QUICK_LAUNCH_SETTINGS  ： // 跳转快速启动设置界面
//        ACTION_SEARCH_SETTINGS    ：    // 跳转到 搜索设置界面
//        ACTION_SECURITY_SETTINGS  ：     // 跳转到安全设置界面
//        ACTION_SETTINGS         // 跳转到设置界面
//        ACTION_SOUND_SETTINGS    // 跳转到声音设置界面
//        ACTION_SYNC_SETTINGS             // 跳转账户同步界面
//        ACTION_USER_DICTIONARY_SETTINGS  //  跳转用户字典界面
//        ACTION_WIFI_IP_SETTINGS        // 跳转到IP设定界面
//        ACTION_WIFI_SETTINGS            //  跳转Wifi列表设置

    }

    /**
     * 跳转系统分享
     *
     * @param context
     */
    public static void intentToShare(Context context,String type,String subject,String text,String desc) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(type);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, desc));
    }


}
