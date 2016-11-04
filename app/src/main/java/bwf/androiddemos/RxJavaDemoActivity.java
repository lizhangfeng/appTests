package bwf.androiddemos;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.OnClick;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.utils.ToastUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJava的使用
 * RxJava的使用教程地址：http://gank.io/post/560e15be2dca930e00da1083
 * 主要查看RxJava的基本概念和使用；
 */
public class RxJavaDemoActivity extends BaseActivity {

    @Bind(R.id.img_rx_test)
    ImageView img_rx_test;

    @Override
    public int getContentViewId() {
        return R.layout.activity_rx_java_demo;
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


    @OnClick({R.id.btn_rxjava, R.id.btn_rxjava_schedule, R.id.btn_rxjava_map, R.id.btn_rxjava_flatmap
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rxjava://rxJava的基本使用
                testRxJava();
                break;
            case R.id.btn_rxjava_schedule://RxJava的线程使用
                testScheduler();
                break;
            case R.id.btn_rxjava_map://变换的使用
                testMap();
                break;
            case R.id.btn_rxjava_flatmap://一对多变换
                testFlatMap();
                break;

        }
    }

    /**
     * 一对多的变换
     */
    private void testFlatMap() {
        MyDrawable[] myDrawables = new MyDrawable[2];
        myDrawables[0] = new MyDrawable();
        myDrawables[1] = new MyDrawable();
        myDrawables[0].drawableIds = new Integer[]{R.mipmap.ic_launcher, R.mipmap.jd_002};
        myDrawables[1].drawableIds = new Integer[]{R.mipmap.jazzy_02, R.mipmap.jazzy_03};

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer drawable) {
                img_rx_test.setImageDrawable(getResources().getDrawable(drawable));
            }

        };


        Observable.from(myDrawables)
                .flatMap(new Func1<MyDrawable, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(MyDrawable myDrawable) {
                        return Observable.from(myDrawable.drawableIds);
                    }
                })
                .subscribe(subscriber);
    }

    /**
     * 变换的使用—— 一对一或者多对多的变换
     * 所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列
     */
    private void testMap() {
        Observable.just(R.mipmap.jd_002) // 输入类型 Integer
                .map(new Func1<Integer, Drawable>() {//先通过map把id转换成Drawable再做处理
                    @Override
                    public Drawable call(Integer id) { // 参数类型 Integer
                        return getResources().getDrawable(id); // 返回类型 Drawable
                    }
                })
                .subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable drawable) { // 参数类型 Bitmap
                        img_rx_test.setImageDrawable(drawable);
                    }
                });
    }

    /**
     * RxJava的线程使用
     */
    private void testScheduler() {
        final Integer[] drawableIds = new Integer[]{R.mipmap.jazzy_01, R.mipmap.jazzy_02, R.mipmap.jazzy_03, R.mipmap.jazzy_04};

        Observable.create(new Observable.OnSubscribe<Drawable>() {

            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                //如果subscribeOn(Schedulers.io())了那么此过程在io线程中执行
                Drawable drawable = getResources().getDrawable(drawableIds[0]);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        img_rx_test.setImageDrawable(drawable);
                    }
                });

        //注：此处subscribe还可以使用Action1，Action1和Func1的使用见教程
    }

    public class MyDrawable {
        public Integer[] drawableIds;
    }

    /**
     * RxJava的使用
     */
    private void testRxJava() {
        //观察者Observer的实现抽象类
        Subscriber<String> observer = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                ToastUtil.showToast("" + s);
            }
        };
        /* ----------------第一种实现：被观察者定义事件触发 ----------------*/
        //被观察者(用于定义事件触发规则)
//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });

         /* ----------------第二种实现：被观察者定义事件触发 ----------------*/
        //被观察者以just方式定义事件触发
//        Observable observable = Observable.just("Hello", "Hi", "Aloha");
        //内部执行操作为
        // subscriber.onNext("Hello");
        // subscriber.onNext("Hi");
        // subscriber.onNext("Aloha");
        // subscriber.onCompleted();

        /* ----------------第三种实现：被观察者定义事件触发 ----------------*/
        String[] arr = {"Hello", "Hi", "Aloha"};
        //被观察者以just方式定义事件触发
        Observable observable = Observable.from(arr);

        //开启订阅
        observable.subscribe(observer);

//        observable.unsubscribe(); 取消订阅

    }

}
