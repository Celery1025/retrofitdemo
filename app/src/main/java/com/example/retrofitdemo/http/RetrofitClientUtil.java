package com.example.retrofitdemo.http;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xieshuilin on 2017/2/16.
 */

public class RetrofitClientUtil {

    protected static ApiService service = null;
    private static OkHttpClient  client = null;

    /**
     * 初始化Retrofit
     */
    public static ApiService getService(Context context){
        if (service == null){
            //获取实例
            Retrofit retrofit = new Retrofit.Builder()
                    //设置OKHttpClient,如果不设置会提供一个默认的
                    .client(getClient(context))
                    //设置baseUrl
//                    .baseUrl("https://api.github.com/")
                    .baseUrl("http://api.k780.com:88/")//post 方法
//                    .baseUrl("http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4")//下载地址
                    //添加Gson转换器
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();


            service = retrofit.create(ApiService.class);
        }
        return service;
    }


    /**
     * log日志拦截
     */
    private static class LoggingInterceptor implements Interceptor {

        private Context mContext;
        public LoggingInterceptor(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request request = chain.request();

            long t1 = System.nanoTime();//请求发起的时间

            String sendLog = String.format("发送请求 %s on %s%n%s", request.url(), chain.connection(), request.headers());
            Log.d("RetrofitClientUtil",sendLog);

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应的时间

            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);

            String reciveLog = String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                    response.request().url(),
                    responseBody.string(),
                    (t2 - t1) / 1e6d,
                    response.headers());

            Log.d("RetrofitClientUtil",reciveLog);
            Log.d("响应时间",((t2 - t1) / 1e6d)+"");

            if (NetworkUtil.isNetworkAvailable(mContext)) {
                int maxAge = 0 * 60; // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }

            return response;
        }
    }

    /**
     * 请求拦截器，修改请求header
     */
    private static class RequestInterceptor implements Interceptor{

        private Context mContext;
        CacheControl controlCache = null;
        public RequestInterceptor(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
                if (NetworkUtil.isNetworkAvailable(mContext)) {
                     controlCache = CacheControl.FORCE_NETWORK;
                    Log.e("有网络","-----");
                }else {
                    controlCache = CacheControl.FORCE_CACHE;
                    Log.e("无网络","-----");
                }
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "text/html; charset=UTF-8")
//                      .addHeader("Accept-Encoding", "*")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .addHeader("Access-Control-Allow-Origin", "*")
                        .addHeader("Access-Control-Allow-Headers", "X-Requested-With")
                        .addHeader("Vary", "Accept-Encoding")
//                      .addHeader("Cookie", "add cookies here")
                        .cacheControl(controlCache)
                        .build();
            Log.e("zcb", "request:" + request.toString());
            Log.e("zcb", "request headers:" + request.headers().toString());
            return chain.proceed(request);
        }
    }


    public static OkHttpClient getClient(Context mContext){
           if (client == null) {
               File cacheFile = new File(mContext.getCacheDir(), "retrofitdemo");
               Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //100Mb
               client = new OkHttpClient.Builder()
                       .addInterceptor(new LoggingInterceptor(mContext))
                       .addInterceptor(new RequestInterceptor(mContext))
                       .cache(cache)
                       .connectTimeout(30, TimeUnit.SECONDS)
                       .build();
           }
       return client;
    }


    /**
     * 组装字符串数据
     * @param paramsMap
     * @return
     */
    public static RequestBody getRequestBody(HashMap<String,String> paramsMap){

        StringBuilder jsonStr = new StringBuilder("");
        for (String key : paramsMap.keySet()) {
            jsonStr.append(key).append("=").append(paramsMap.get(key)).append("&");
        }
        jsonStr.delete(jsonStr.length()-1,jsonStr.length());
        System.out.print(jsonStr.toString());
        RequestBody body= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonStr.toString());
        return body;
    }


}
