package bwf.androiddemos.utils;

import android.os.Handler;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by lizhangfeng on 16/3/29.
 * description:
 */
public class FileUtil {

    public static File getFileFromBytes(byte[] b, File ret) {

        BufferedOutputStream stream = null;
        try {

            FileOutputStream fstream = new FileOutputStream(ret);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    /**
     * 在系统data下创建目录和文件
     */
    public static void mkdirToSystemData() {
        //给data目录777权限
        CMDUtils.exeCommand("chmod 777 system");
        //此处给延迟是因为要用户同意root权限
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BufferedWriter writer;
                try {
                    File file = new File("system/bwf.test");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(file.getAbsoluteFile() + "/name.txt");
                    FileOutputStream out = new FileOutputStream(file2);
                    writer = new BufferedWriter(new OutputStreamWriter(out));
                    try {
                        writer.write("123123");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, 5000);
    }

}
