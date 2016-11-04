package bwf.androiddemos.http.api;

import java.util.Map;

import bwf.androiddemos.bean.SubBaseBean;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by Lizhangfeng on 2016/10/25 0025.
 * Description:
 */

public interface UpLoadApi {

    @Multipart
    @POST("jspExample/upload")
    Call<SubBaseBean> upload(@Part("file\"; filename=\"1.png") RequestBody file);
    //@Part(“fileDes”) 文件路径
    //@Part(“file\”; filename=\”1.png”) 上传文件的file

    @POST("jspExample/upload")
    Call<SubBaseBean> upload_2(@PartMap Map<String,RequestBody> params);//可以上传多个
}
