package com.example.retrofitdemo.http;

import com.example.retrofitdemo.model.DateTime;
import com.example.retrofitdemo.model.TimeInfo;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by xieshuilin on 2017/2/16.
 * 不涉及上传文件和下载文件进度监听的Api
 * 上传文件和下载文件用okhttp封装
 */
public interface ApiService {

    //http://api.k780.com:88/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
    //http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4


    @GET("/?app=ip.get&ip=8.8.8.8&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json")
    Observable<DateTime> getRetrofitData();

    //表格形式发送
    @FormUrlEncoded
    @POST("/")
    Call<TimeInfo> getTimeInfoPostForm(@FieldMap Map<String, String> map);


//    //json 字符串的形式发送
//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @POST("/")
//    Call<T> getTimeInfoPostString(@Body RequestBody requestBody);


}
