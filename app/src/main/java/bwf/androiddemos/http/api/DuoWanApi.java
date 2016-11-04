package bwf.androiddemos.http.api;

import bwf.androiddemos.bean.TopResultBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static bwf.androiddemos.RetrofitActivity.UPDATE;

/**
 * Created by Lizhangfeng on 2016/10/25 0025.
 * Description:
 * 定义接口
 * TopResultBean 为服务器返回数据结构的实体类
 */

public interface DuoWanApi {
    @GET(UPDATE)
    //此处声明请求方法和接口地址（接口地址不带baseUrl）
    Call<TopResultBean> doUpdate(@Query("start") int start, @Query("count") int count);
    //doUpdate方法中为接口参数列表

    /**
     关于这几个注解的定义如下：
     @Path：所有在网址中的参数（URL的问号前面），如： http://102.10.10.132/api/Accounts/{accountId}
     @Query：URL问号后面的参数，如： http://102.10.10.132/api/Comments?access_token={access_token}
     @QueryMap：相当于多个@Query
     @Field：用于POST请求，提交单个数据
     @Body：相当于多个@Field，以对象的形式提交
     */
}
