package com.example.retrofitdemo.http;

/**
 * Created by Zhou on 2017/3/20.
 */

public interface UpLoadProgressListener {
    void onLoading(long current, long total, boolean done);
    void onSuccess(boolean bool,String response);
    void onFailure(int code, String Msg, Exception e);
}
