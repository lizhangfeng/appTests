package bwf.androiddemos.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Lizhangfeng on 2016/10/9 0009.
 * Description: qq 相关帮助类
 */
public class QQUtils {

    /**
     * 指定QQ临时会话
     * @param context
     * @param qq
     */
    public static void converstaionToQQ(Context context, String qq) {

        String str = "";
        //判断QQ是否安装（“*”是需要联系QQ号）
        if (SetUpCheckUtils.isQQClientAvailable(context)) {
            //安装了QQ会直接调用QQ，打开手机QQ进行会话
            str = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq + "&version=1&src_type=web&web_src=oicqzone.com";
        } else {
            //没有安装QQ会展示网页
            str = "http://wpa.qq.com/msgrd?v=3&uin=" + qq + "&site=qq&menu=yes";
        }
        Uri uri = Uri.parse(str);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(it);
    }

}
