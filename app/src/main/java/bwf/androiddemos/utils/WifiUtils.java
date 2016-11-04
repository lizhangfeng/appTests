package bwf.androiddemos.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/9/21 0021.
 * Description:
 */
public class WifiUtils {


    private static final String TAG = "wifi_manager" ;

    /**
     * android.permission.ACCESS_WIFI_STATE android.permission.CHANGE_WIFI_STATE
     * android.permission.WAKE_LOCK 开启wifi最好使用异步
     */
    public static void toggleWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        } else {
            wifiManager.setWifiEnabled(true);
        }
    }

    public static void getScanWifiResults(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiResults = wifiManager.getScanResults();
        for (ScanResult wifi : wifiResults) {
            LogUtils.d(TAG, wifi.toString());
        }

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        LogUtils.d(TAG, TelephonyManager.PHONE_TYPE_GSM + "----" + tm.getPhoneType());
        List<NeighboringCellInfo> cellResults = tm.getNeighboringCellInfo();
        for (NeighboringCellInfo cell : cellResults) {
            LogUtils.d(TAG, cell.getCid() + "-" + cell.getLac() + "-" + cell.getRssi() + "-" + cell.getPsc() + "-"
                    + cell.getNetworkType());
        }
        LogUtils.d(TAG, getProviderName(context).getText());
    }

    /**
     * 获取SIM卡的IMSI码 SIM卡唯一标识：IMSI 国际移动用户识别码 （IMSI：International Mobile
     * Subscriber Identification Number）是区别移动用户的标志， 储存在SIM卡中，可用于区别移动用户的有效信息。
     * IMSI由MCC、MNC、MSIN组成，其中MCC为移动国家号码，由3位数字组成，
     * 唯一地识别移动客户所属的国家，我国为460；MNC为网络id，由2位数字组成， 用于识别移动客户所归属的移动网络，中国移动为00，中国联通为01,
     * 中国电信为03；MSIN为移动客户识别码，采用等长11位数字构成。 唯一地识别国内GSM移动通信网中移动客户。
     * 所以要区分是移动还是联通，只需取得SIM卡中的MNC字段即可
     */
    public static ProviderName getProviderName(Context context) {
        String imsi = getIMSI(context);
        if (imsi != null) {
            // 因为移动网络编号46000下的IMSI已经用完,所以虚拟了一个46002编号，134/159号段使用了此编号
            LogUtils.i("imsi", imsi);
            if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007")) {
                return ProviderName.chinaMobile;
            } else if (imsi.startsWith("46001")) {
                return ProviderName.chinaUnicom;
            } else if (imsi.startsWith("46003")) {
                return ProviderName.chinaTelecom;
            } else {
                return ProviderName.other;
            }
        } else {
            return ProviderName.other;
        }
    }

    public static enum ProviderName {
        chinaMobile("中国移动"), chinaUnicom("中国联通"), chinaTelecom("中国电信"), chinaNetcom("中国网通"), other("未知");
        private String text;

        private ProviderName(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    /**
     * 获取wifi名称
     *
     * @param context
     * @return
     */
    public static String getWifiName(Context context) {
        String wifiname = "";
        if (isWifiActive(context)) {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            if (info != null) {
                if (Build.VERSION.SDK_INT >= 14) {
                    wifiname = info.getSSID().substring(1, info.getSSID().length() - 1);
                } else {
                    wifiname = info.getSSID();
                }
            }
        }
        return wifiname;
    }

    /**
     * wifi是否可用
     * @param icontext
     * @return
     */
    public static boolean isWifiActive(Context icontext) {
        Context context = icontext.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info;
        if (connectivity != null) {
            info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * IMSI 全称为 International Mobile Subscriber
     * Identity，中文翻译为国际移动用户识别码。它是在公众陆地移动电话网
     * （PLMN）中用于唯一识别移动用户的一个号码。在GSM网络，这个号码通常被存放在SIM卡中
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        TelephonyManager ts = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return ts.getSubscriberId();
    }

    /**
     * 获取wifi的mac地址
     *
     * @param context
     * @return
     */
    public static String getWifiMac(Context context) {
        String mac = "";
        if (isWifiActive(context)) {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String maxText = info.getBSSID();
            mac = maxText;
//			LogUtils.e("wo的mac地址", mac);
        }
        return mac;
    }

    /**
     * 获取路由器Mac
     */
    public static String getRouterMac(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifi.getConnectionInfo().getBSSID();
    }

}
