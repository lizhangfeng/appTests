package bwf.androiddemos.http.api;

import bwf.androiddemos.bean.TopResultBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static bwf.androiddemos.RetrofitActivity.UPDATE;

/**
 * Created by Lizhangfeng on 2016/10/25 0025.
 * Description:
 * 定义接口
 * TopResultBean 为服务器返回数据结构的实体类
 */

public interface DuoWanApi2 {

    @GET(UPDATE)
    Observable<TopResultBean> doUpdate(@Query("start") int start, @Query("count") int count);
    //此处和DuoWanApi不同的是返回对象是Observable
}
