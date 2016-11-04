package bwf.androiddemos.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 注册广播的工具
 * Created by Administrator on 2015/10/30.
 */
public class BroadcastReceiverUtils {


    private Context context;
    private OnBroadcastReceiverListener listener;
    private BroadcastReceiver receiver;

    public BroadcastReceiverUtils(Context context, final String action) {
        this.context = context;
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String a = intent.getAction();
                if (action.equals(a) && listener != null) {
                    listener.onReceive(context, intent);
                }

            }
        };
        IntentFilter filter = new IntentFilter(action);
        context.registerReceiver(receiver, filter);
    }


    public void setOnBroadcastReceiverListener(OnBroadcastReceiverListener listener) {
        this.listener = listener;

    }


    /**
     * 注销广播
     */
    public void unregisterReceiver() {

        if (receiver != null) {
            context.unregisterReceiver(receiver);
        }

    }

    public interface OnBroadcastReceiverListener {

        void onReceive(Context context, Intent intent);

    }

    /**
     * 发送广播
     */
    public static void sendBroad(Activity activity, String action) {
        Intent intent = new Intent(action);
        activity.sendBroadcast(intent);
    }

}
