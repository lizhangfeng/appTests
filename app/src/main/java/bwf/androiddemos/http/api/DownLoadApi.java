package bwf.androiddemos.http.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Lizhangfeng on 2016/10/25 0025.
 * Description:
 */

public interface DownLoadApi{

    @POST("hotvideo/real/fdadf608f4b64dad908d7d8774795d8e.jpg")
    Call<ResponseBody> downloadFile();
}
