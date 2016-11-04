package bwf.androiddemos.utils;

import android.net.Uri;

/**
 * 类名称 ：StringUtil 类描述 ：String判断处理工具类 创建人 ：李章丰 创建时间：下午3:02:09
 */
public class StringUtil {

    /**
     * 判断字符串是否为null或者空字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        boolean result = false;
        if (null == str || "".equals(str.trim())) {
            result = true;
        }
        return result;
    }

    /**
     * 如果i小于10，添加0后生成string
     *
     * @param i
     * @return
     */
    public static String addZreoIfLessThanTen(int i) {

        String string = "";
        int ballNum = i + 1;
        if (ballNum < 10) {
            string = "0" + ballNum;
        } else {
            string = ballNum + "";
        }
        return string;
    }

    /**
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
        if (null != string && !"".equals(string.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 去掉一个字符串中的所有的单个空格" "
     *
     * @param string
     */
    public static String replaceSpaceCharacter(String string) {
        if (null == string || "".equals(string)) {
            return "";
        }
        return string.replace(" ", "");
    }

    /**
     * 获取小数位为6位的经纬度
     *
     * @param string
     * @return
     */
    public static String getStringLongitudeOrLatitude(String string) {
        if (StringUtil.isEmpty(string)) {
            return "";
        }
        if (string.contains(".")) {
            String[] splitArray = string.split("\\.");
            if (splitArray[1].length() > 6) {
                String substring = splitArray[1].substring(0, 6);
                return splitArray[0] + "." + substring;
            } else {
                return string;
            }
        } else {
            return string;
        }
    }

    /**
     * 转化成int
     *
     * @param s
     * @return
     */
    public static int getInteger(String s) {
        return isEmpty(s) ? 0 : Integer.parseInt(s);
    }

    /**
     * 获取数量s
     *
     * @param s
     * @return
     */
    public static String getNumString(String s) {
        return isEmpty(s) || "null".equals(s) ? "0" : s;
    }


    /**
     * 距离处理
     *
     * @param distance
     */
    public static String getDistance(String distance) {
        if (StringUtil.isEmpty(distance) || "null".equals(distance))
            return "";
        try {
            float dis = Float.parseFloat(distance);
            if (dis > 1000) {

                String ds = "" + dis / 1000;

                int index = ds.indexOf(".");
                if (index != -1)
                    return ds.substring(0, index + 2) + "km";
                return ds + "km";

            } else {
                return "" + (int) dis + "m";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取简介
     * @param desc
     * @return
     */
    public static String getDesc(String desc) {
        return isEmpty(desc) ? "无" : desc;
    }

    /**
     * 判断是不是一个url
     *
     * @param url
     * @return
     */
    public static boolean isCorrectUrl(String url) {
        if (isEmpty(url))
            return false;

        if (url.startsWith("http"))
            return true;
        if (url.startsWith("fttp"))
            return true;

        return false;
    }

    /**
     * 显示消息的数量
     *
     * @param count
     * @return
     */
    public static String getMsgCount(int count) {
        if (count > 99)
            return "99+";
        return "" + count;
    }

    /**
     * 通过string获取uri
     *
     * @param url
     * @return
     */
    public static Uri getUri(String url) {
        return Uri.parse(isEmpty(url) ? "" : url);
    }

}
