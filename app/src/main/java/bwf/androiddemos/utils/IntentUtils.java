package bwf.androiddemos.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by lizhangfeng on 16/6/4.
 * description: 跳转utils
 */
public class IntentUtils {

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    public static void openActivity(Context context, Class<?> pClass) {
        openActivity(context, pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    public static void openActivity(Context context, Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(context, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
    }

    /**
     * 通过Action启动Activity
     *
     * @param pAction
     */
    public void openActivity(Context context, String pAction) {
        openActivity(context, pAction, null);
    }

    /**
     * 通过Action启动Activity，并且含有Bundle数据
     *
     * @param pAction
     * @param pBundle
     */
    public static void openActivity(Context context, String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
    }


}
