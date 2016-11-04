package bwf.androiddemos.http;

/**
 * Created by Lizhangfeng on 2016/10/25 0025.
 * Description: 下载进度监听s
 */

public interface ProgressListener {
    /**
     * @param progress 已经下载或上传字节数
     * @param total    总字节数
     * @param done     是否完成
     */
    void onProgress(long progress, long total, boolean done);

}
