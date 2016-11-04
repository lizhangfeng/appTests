package bwf.androiddemos.utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;

import java.util.List;

/**
 * Created by lizhangfeng on 16/1/20.
 */
public class LockUtil {

    /**
     * 判断是否锁屏
     *
     * @param c
     * @return
     */
    public static boolean isScreenLocked(Context c) {
        boolean isScreenLocked;
        KeyguardManager mKeyguardManager = (KeyguardManager) c.getSystemService(c.KEYGUARD_SERVICE);
        PowerManager pm = (PowerManager) c.getSystemService(Context.POWER_SERVICE);
        if (mKeyguardManager.inKeyguardRestrictedInputMode()) {
            isScreenLocked = true;
        } else {
            isScreenLocked = false;

        }
        if (!pm.isScreenOn()) {
            isScreenLocked = true;
        }
        return isScreenLocked;
    }

    /**
     * 是否在前台运行
     *
     * @param @param  context
     * @param @return
     * @return boolean
     * @throws
     */
    public static boolean isRunningForeground(Context context) {
        boolean isTop = false;
        if (android.os.Build.VERSION.SDK_INT <= 20) {
            String packageName = context.getPackageName();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
            if (tasksInfo.size() > 0) {
                // 应用程序位于堆栈的顶层
                if (packageName.equals(tasksInfo.get(0).topActivity.getPackageName())) {
                    isTop = true;
                }
            }
        } else {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            if (appProcesses == null)
                return false;

            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.processName.equals(context.getPackageName())
                        && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    isTop = true;
                }
            }
        }

        return isTop;
    }
}
