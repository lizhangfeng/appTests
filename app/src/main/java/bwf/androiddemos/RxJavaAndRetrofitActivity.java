package bwf.androiddemos;

import android.view.View;

import butterknife.OnClick;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.bean.TopResultBean;
import bwf.androiddemos.http.api.DuoWanApi2;
import bwf.androiddemos.utils.ToastUtil;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static bwf.androiddemos.RetrofitActivity.BASE_URL;

/**
 * Rxjava+Retrofit的使用
 * RxJava + Retrofit使用教程：http://gank.io/post/56e80c2c677659311bed9841
 */
public class RxJavaAndRetrofitActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_rx_java_and_retrofit;
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

    @OnClick(R.id.btn_rxjava_retrofit)
    public void onClick(View v) {
        getDataByRxJavaAndRetrofit();
    }

    /**
     * RxJava + Retrofit对于普通网络请求的使用，关于简略封装请参考
     * http://gank.io/post/56e80c2c677659311bed9841
     */
    private void getDataByRxJavaAndRetrofit() {
        /**
         * 创建一个Retrofit对象
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//baseUrl 一定要以“/”结尾
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器，用于服务器返回数据的解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        /**
         * 此处并没有使用okhttp的网络线程进行请求网络而是把请求网络的操作放入
         * RxJava的io线程管理并且通过被观察者订阅事件通知给观察者进行Ui操作
         * （通过RxAndroid中提供的AndroidSchedulers.mainThread()使之切换到主线程通知Ui操作）
         */

        Subscriber subscriber = new Subscriber<TopResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showToast(e.getMessage());
            }

            @Override
            public void onNext(TopResultBean topResultBean) {
                ToastUtil.showToast("服务器返回: " + topResultBean.toString());
            }
        };

        DuoWanApi2 duoWanApi2 = retrofit.create(DuoWanApi2.class);
        duoWanApi2.doUpdate(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

//        subscriber.unsubscribe(); 取消订阅此处相当于call.cancel();

    }
}
