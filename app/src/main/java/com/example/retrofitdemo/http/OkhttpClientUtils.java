package com.example.retrofitdemo.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xsl on 2017/3/20.
 * 通过okhttp3 封装上传下载文件进度
 */
public class OkhttpClientUtils {

    //服务器异常
    public final static int SERVER_EXCEPTION_CODE = 500;
    //无网络
    public final static int NO_NETWORK_CODE = 400;
    //服务器连接异常描述
    public final static String SERVER_EXCEPTION = "服务器连接异常";
    //无网络描述
    public final static String NO_NETWORK = "网络不可用";

    private static OkHttpClient okHttpClient = null;
    private Handler handler = new Handler(Looper.getMainLooper());

    /**********************************************文件上传下载***********************************************************/

    private static OkHttpClient getOkHttpClientInstance(){
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
            okHttpClient.newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }


    /**
     * 上传文件
     * @param context 上下文
     * @param params 参数
     * @param file 文件
     * @param url 上传完整地址
     * @param upLoadProgressListener 上传进度监听接口
     */
    public void upLoad(Context context, HashMap<String,String> params, File file, String url, final UpLoadProgressListener upLoadProgressListener){
        OkHttpClient okHttpClient = getOkHttpClientInstance();
        MultipartBody.Builder builder= new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加其他参数要在文件之前
        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                builder.addFormDataPart(entry.getKey(),  URLEncoder.encode(entry.getValue(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        builder.addFormDataPart("file", file.getName(),
                RequestBody.create(MediaType.parse("multipart/form-data;boundary=\"+BOUNDARY"), file));

        Request request = new Request.Builder()
                .tag(context)
                .addHeader("source","android")
                .url(url)
                .post(new CeleryRequestBody(builder.build()) {
                    @Override
                    public void loading(long current, long total, boolean done) {
                        upLoadProgressListener.onLoading(current, total, done);
                    }
                })
                .build();

        Log.e("request UpLoad", request.toString());

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                try {
                    call.cancel();
                    NetworkUtil.pingBaiDu(new NetworkUtil.pingListerner() {
                        @Override
                        public void onSuccess(boolean bool) {
                            upLoadProgressListener.onFailure(SERVER_EXCEPTION_CODE, SERVER_EXCEPTION, e);
                        }

                        @Override
                        public void onFailure(int code, boolean bool) {
                            upLoadProgressListener.onFailure(NO_NETWORK_CODE, NO_NETWORK, null);
                        }
                    }, handler);
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    final String body = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (response.isSuccessful() && response.code() == 200) {
                                upLoadProgressListener.onSuccess(true, body);
                                upLoadProgressListener.onLoading(100, 100, true);
                            } else {
                                upLoadProgressListener.onFailure(response.code(), response.message(), null);
                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 下载文件
     * @param context 上下文
     * @param url 下载地址
     * @param downLoadProgressListener 下载进度接口监听
     */
    public void downLoad(Context context, String url, final DownLoadProgressListener downLoadProgressListener){
        Request request = new Request.Builder()
                .tag(context)
                .url(url)
                .build();
        Log.e("request DownLoad", request.toString());

        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient = okHttpClient.newBuilder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return   originalResponse.newBuilder().body(new CeleryResponseBody(originalResponse.body()) {
                    @Override
                    public void onResponseProgress(long current, long total, boolean done) {
                        downLoadProgressListener.onLoading(current,total,done);
                    }
                }).build();
            }
        }).connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                call.cancel();
                NetworkUtil.pingBaiDu(new NetworkUtil.pingListerner() {
                    @Override
                    public void onSuccess(boolean bool) {
                        downLoadProgressListener.onFailure(SERVER_EXCEPTION_CODE, SERVER_EXCEPTION, e);
                    }

                    @Override
                    public void onFailure(int code, boolean bool) {
                        downLoadProgressListener.onFailure(NO_NETWORK_CODE, NO_NETWORK, null);
                    }
                }, handler);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        byte[] bytes = null;
                        try {
                            bytes = response.body().bytes();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        final byte[] finalBytes = bytes;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful() && response.code() == 200) {
                                    downLoadProgressListener.onSuccess(finalBytes);
                                } else {
                                    downLoadProgressListener.onFailure(response.code(),response.message(), null);
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }

    //    public static String savaFile(InputStream inputStream,String fileName){
//
//        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            Log.e("", "SD卡不存在");
//            return null;
//        }
//        //可以在这里自定义路径
//        File file = new File(Environment.getExternalStorageDirectory() + File.separator + Constants.APP_FILE_NAME);
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        File file1 = new File(file.getPath()+ File.separator+fileName);
//        FileOutputStream fileOutputStream = null;
//        try {
//            int len;
//            byte[] buf = new byte[2048];
//            fileOutputStream = new FileOutputStream(file1);
//            while ((len = inputStream.read(buf)) != -1) {
//                fileOutputStream.write(buf, 0, len);
//            }
//            return file1.getPath();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                fileOutputStream.flush();
//                fileOutputStream.close();
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return null;
//    }

}
