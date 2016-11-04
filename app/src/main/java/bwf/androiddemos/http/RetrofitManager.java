package bwf.androiddemos.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static bwf.androiddemos.RetrofitActivity.BASE_URL;

/**
 * Created by Lizhangfeng on 2016/10/25 0025.
 * Description:Retrofit简单封装
 */

public class RetrofitManager {

    private static RetrofitManager retrofitManager;

    private Retrofit retrofit;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//baseUrl 一定要以“/”结尾
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器，用于服务器返回数据的解析
                .build();

    }

    public static synchronized RetrofitManager getInstance() {
        if (retrofitManager == null)
            retrofitManager = new RetrofitManager();
        return retrofitManager;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
