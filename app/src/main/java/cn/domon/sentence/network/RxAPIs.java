package cn.domon.sentence.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Domon on 16-11-22.
 */

public interface RxAPIs {
    @GET("meitumeiju?page=1")
    Call<ResponseBody> test();


    @GET("meitumeiju/shouxiemeiju?page=1")
    Call<ResponseBody> test1();
}
