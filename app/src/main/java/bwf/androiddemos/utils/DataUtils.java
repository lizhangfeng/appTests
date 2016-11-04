package bwf.androiddemos.utils;

import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/13.
 */
public class DataUtils {

    /**
     * Data转时间戳
     * */
    public static String DataToStamp(String data) {
        DebugUtils.Log_V("data=" + data);
        if(TextUtils.isEmpty(data)){
            return "";
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(data);
            DebugUtils.Log_V("Format To times:" + date.getTime());
            return String.valueOf(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
