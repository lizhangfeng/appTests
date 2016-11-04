package bwf.androiddemos.utils;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * Created by Lizhangfeng on 2016/9/23 0023.
 * Description:
 */
public class CMDUtils {

    public static void exeCommand(String CMD){
        try {
            Process p = Runtime.getRuntime().exec("su");
            //获取输出流
            OutputStream outputStream = p.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            //将命令写入
            dataOutputStream.writeBytes(CMD);
            //提交命令
            dataOutputStream.flush();
            //关闭流操作
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
