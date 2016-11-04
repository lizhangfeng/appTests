package bwf.androiddemos.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 * 需要导入fastson jar包
 */
public class JsonUtils {


    /**
     * 解析成对象
     */
    public static <T> T parseBean(String text, Class<T> clazz) throws JSONException {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return JSON.parseObject(text, clazz);
    }

    /**
     * 解析成数组
     */
    public static <T> List<T> parseBeans(String text, Class<T> clazz) throws JSONException {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return JSON.parseArray(text, clazz);
    }


    /**
     * 把对象转成json
     */
    public static String getString(Object object) throws JSONException {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }


    public enum JSON_TYPE {
        /**
         * JSONObject
         */
        JSON_TYPE_OBJECT,
        /**
         * JSONArray
         */
        JSON_TYPE_ARRAY,
        /**
         * 不是JSON格式的字符串
         */
        JSON_TYPE_ERROR
    }

    /***
     * 获取JSON类型
     * 判断规则
     * 判断第一个字母是否为{或[ 如果都不是则不是一个JSON格式的文本
     *
     * @param str
     * @return
     */
    public static JSON_TYPE getJsonType(String str) {
        if (TextUtils.isEmpty(str)) {
            return JSON_TYPE.JSON_TYPE_ERROR;
        }
        str = str.trim();

        final char[] strChar = str.substring(0, 1).toCharArray();
        final char firstChar = strChar[0];

        if (firstChar == '{') {
            return JSON_TYPE.JSON_TYPE_OBJECT;
        } else if (firstChar == '[') {
            return JSON_TYPE.JSON_TYPE_ARRAY;
        } else {
            return JSON_TYPE.JSON_TYPE_ERROR;
        }
    }
}
