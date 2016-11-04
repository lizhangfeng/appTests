package bwf.androiddemos.utils;

/**
 * unicode转utf-8格式
 */
public class UnicodeToUtf8 {
    // 转换字符串
    public static String UNICODE_TO_UTF8(String pname) {
        String strreplace;
        if (pname.contains("%3A")) {
            strreplace = pname.replace("%3A", ":");
        } else {
            strreplace = pname;
        }

        if (strreplace.contains("%40")) {
            strreplace = strreplace.replace("%40", "@");
        }
        if (strreplace.contains("%20")) {
            strreplace.replace("%20", " ");
        }


        return strreplace;
    }
}
