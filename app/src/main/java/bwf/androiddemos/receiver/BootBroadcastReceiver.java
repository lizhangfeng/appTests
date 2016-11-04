package bwf.androiddemos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import bwf.androiddemos.MainActivity;

/**
 * Created by Lizhangfeng on 2016/9/23 0023.
 * Description:
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION)) {
            Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);
        }
    }
}