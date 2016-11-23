package cn.domon.sentence.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Domon on 16-11-22.
 */

public interface RxAPIs {
    @GET("meitumeiju?")
    Call<ResponseBody> reqMTMJ(@Query("page") int index);

    @GET("meitumeiju/shouxiemeiju?")
    Call<ResponseBody> reqSXMJ(@Query("page") int index);

    @GET("meitumeiju/jingdianduibai?")
    Call<ResponseBody> reqJDDB(@Query("page") int index);
}
