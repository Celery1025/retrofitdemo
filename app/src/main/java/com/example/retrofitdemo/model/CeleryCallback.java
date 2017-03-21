package com.example.retrofitdemo.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Zhou on 2017/3/14.
 */

public interface CeleryCallback<T> {
    void onResponse(Call<T> call, T response);
    void onFailure(Call<T> call, Throwable t);
}
