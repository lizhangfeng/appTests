package bwf.androiddemos.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 */
public class DebugUtils {

    private static final String TAG="cdy";

    /**
     * 获取测试List数据
     * @param clazz
     * @param num 返回数量
     * @param <T> 传入要测试的数据类型
     * @return
     */
    public static <T> List<T> getList(Class<T> clazz, int num) {

        List list = new ArrayList();

        try {
            for (int i = 0; i < num; i++) {
                T t = clazz.newInstance();
                list.add(t);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }

        return list;

    }


    public static void Log_V(String text) throws NullPointerException{
        Log.v(TAG,text);
    }




}
