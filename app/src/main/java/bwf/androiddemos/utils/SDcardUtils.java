package bwf.androiddemos.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by Administrator on 2016/5/5.
 */
public class SDcardUtils {


    /**
     * SD卡是否存在
     */
    public static boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }


    /**
     * 获取SD卡跟目录
     */
    public static String getSDPath(Context context) {
        File sdDir = null;
        if (ExistSDCard()) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        } else {
            LogUtils.e("SD卡不存在");
            return "";
        }
        String path = sdDir.toString();
        DebugUtils.Log_V("SD卡跟目录=" + path);
        return path;
    }

    /**
     * 文件目录：如sensecp/pic
     * @param fileDir
     * @return
     */
    public static String getSDPath(String fileDir) {
        File sdcardDir = Environment.getExternalStorageDirectory();
        String path = sdcardDir.getParent() + "/" + sdcardDir.getName();
        final String fileDirectory = path + "/" + fileDir;
        return fileDirectory;
    }


    /**
     * SD卡剩余空间
     */
    public long getSDFreeSize() {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        //返回SD卡空闲大小
        //return freeBlocks * blockSize;  //单位Byte
        //return (freeBlocks * blockSize)/1024;   //单位KB
        return (freeBlocks * blockSize) / 1024 / 1024; //单位MB
    }

    /**
     * SD卡总容量
     */
    public long getSDAllSize() {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //获取所有数据块数
        long allBlocks = sf.getBlockCount();
        //返回SD卡大小
        //return allBlocks * blockSize; //单位Byte
        //return (allBlocks * blockSize)/1024; //单位KB
        return (allBlocks * blockSize) / 1024 / 1024; //单位MB
    }

    /**
     * 判断SD卡上的文件是否存�?
     * @param fileDir 文件目录 如：sensecp/pic
     * @param fileName 文件�?如：123.png
     * @return
     */
    public static boolean isFileOnSDCard(String fileDir,String fileName){
        /**
         * 如果连SD卡都没有就别提还存文件了
         */
        if(!ExistSDCard()){
            return false;
        }

        String fileDirectory = getSDPath(fileDir);
        //创建图片的文�?
        File file = new File(fileDirectory,fileName);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

}
