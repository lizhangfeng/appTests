package bwf.androiddemos.utils;

import android.os.Handler;
import android.os.Message;

/**
 * Created by lizhangfeng on 16/5/31.
 * description: 极光推送相关
 */
public class JpushUtils {

    private static final int MSG_RESET_ALIAS = 0x0010;

    /**
     * 设置失败重新设置
     */
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RESET_ALIAS:
                    setTagAndAlias((String) msg.obj);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 设置极光推送的别名alias
     * @param userId
     */
    public static void setTagAndAlias(String userId) {
//        JPushInterface.setAliasAndTags(App.getApp(), userId, null, new TagAliasCallback() {
//            @Override
//            public void gotResult(int code, String alias, Set<String> tags) {
//                String logs;
//                switch (code) {
//                    case 0:
//                        logs = "Set tag and alias success";
//                        mHandler.removeCallbacksAndMessages(null);
//                        break;
//
//                    case 6002:
//                        logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
//                        if (NetWorkUtil.isNetDeviceAvailable(App.getApp())) {
//                            mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_RESET_ALIAS, alias), 1000 * 60);
//                        } else {
//                            logs = "No network";
//                        }
//                        break;
//
//                    default:
//                        logs = "Failed with errorCode = " + code;
//                }
//                Log.e("Vae+", logs);
//
//            }
//        });
    }

}
