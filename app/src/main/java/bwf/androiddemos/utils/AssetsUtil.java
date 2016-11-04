package bwf.androiddemos.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 获取Assets文件夹下得文件
 */
public class AssetsUtil {

    /**
     * 读取模型
     *
     * @param context
     * @return
     */
    public static byte[] readModel(Context context) {
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count = -1;
        try {
            inputStream = context.getAssets().open("model");
            while ((count = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, count);
            }
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static InputStream getInputStrean(Context context,String fileName) {
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(fileName);
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}