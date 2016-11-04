package bwf.androiddemos;

import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.OnClick;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.bean.SubBaseBean;
import bwf.androiddemos.bean.TopResultBean;
import bwf.androiddemos.http.ProgressListener;
import bwf.androiddemos.http.ProgressResponseBody;
import bwf.androiddemos.http.api.DownLoadApi;
import bwf.androiddemos.http.api.DuoWanApi;
import bwf.androiddemos.http.api.UpLoadApi;
import bwf.androiddemos.utils.LogUtils;
import bwf.androiddemos.utils.ToastUtil;
import bwf.androiddemos.widget.LoadingView;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit的使用(包括普通的网络请求，上传文件、下载文件)
 */
public class RetrofitActivity extends BaseActivity {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    //豆瓣电影的Top250
    public static final String UPDATE = "top250";

    @Bind(R.id.ll_retrofit)
    LinearLayout ll_retrofit;

    private LoadingView loadingView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_retrofit;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.btn_retrofit_test, R.id.btn_retrofit_upload, R.id.btn_retrofit_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_retrofit_test://retrofit可以进行简单的封装使用,比如Retrofit和Service对象的创建过程
                getTop250();
                break;
            case R.id.btn_retrofit_upload://上传文件
                uploadFile();
                break;
            case R.id.btn_retrofit_download://文件下载
                downLoadFile();
                break;
        }
    }

    /**
     * retrofit普通网络请求的使用
     */
    private void getTop250() {
        //加载进度条
        loadingView = new LoadingView(this);
        loadingView.showPopWindow(ll_retrofit);
        /**
         * 创建一个Retrofit对象
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//baseUrl 一定要以“/”结尾
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器，用于服务器返回数据的解析
                .build();

        //获取一个DuoWanService对象
        DuoWanApi updateService = retrofit.create(DuoWanApi.class);
        Call<TopResultBean> updateBean = updateService.doUpdate(0, 10);
        //执行网络操作并返回结果
        updateBean.enqueue(new Callback<TopResultBean>() {
            @Override
            public void onResponse(Call<TopResultBean> call, Response<TopResultBean> response) {
                ToastUtil.showToast("服务器返回: " + response.body().toString());
                loadingView.dismiss();
            }

            @Override
            public void onFailure(Call<TopResultBean> call, Throwable t) {
                ToastUtil.showToast("errMsg:" + t.getMessage());
                loadingView.dismiss();
            }
        });

        //updateBean.cancel(); 取消当前请求
    }

    /**
     * 下载文件
     */
    private void downLoadFile() {
        loadingView = new LoadingView(this);
        loadingView.showPopWindow(ll_retrofit);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://res.imtt.qq.com/");

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Response orginalResponse = chain.proceed(chain.request());

                        return orginalResponse.newBuilder()
                                .body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {
                                    @Override
                                    public void onProgress(long progress, long total, boolean done) {
                                        LogUtils.e("onProgress: " + "total ---->" + total + "done ---->" + progress);
                                    }
                                })).build();
                    }
                }).build();
        Retrofit retrofit3 = builder.client(client).build();
        DownLoadApi downLoadApi = retrofit3.create(DownLoadApi.class);
        Call<ResponseBody> responseBodyCall = downLoadApi.downloadFile();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loadingView.dismiss();
                //此处为下载文件之后存储处理
                try {
                    InputStream is = response.body().byteStream();
                    File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                    fos.close();
                    bis.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ToastUtil.showToast("下载成功，文件存储到：" + Environment.getExternalStorageDirectory() + "/test.jpg");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * 上传文件
     */
    private void uploadFile() {
        String base_url = "http://118.178.142.34/";
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(base_url)//baseUrl 一定要以“/”结尾
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器，用于服务器返回数据的解析
                .build();
        UpLoadApi apiService = retrofit2.create(UpLoadApi.class);
        File file = new File("/storage/emulated/0/DCIM/P61103-095623.jpg");//注意此处路径要是正确
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);

        Call<SubBaseBean> call = apiService.upload(requestBody);
        call.enqueue(new Callback<SubBaseBean>() {
            @Override
            public void onResponse(Call<SubBaseBean> call, Response<SubBaseBean> response) {
                ToastUtil.showToast("上传成功：" + response.body().toString());
            }

            @Override
            public void onFailure(Call<SubBaseBean> call, Throwable t) {
                ToastUtil.showToast("上传失败" + t.getMessage());
            }
        });

    }

}
