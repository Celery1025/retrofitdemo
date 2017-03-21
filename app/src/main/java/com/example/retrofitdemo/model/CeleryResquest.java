package com.example.retrofitdemo.model;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Zhou on 2017/3/14.
 */

public class CeleryResquest<T>  {

    private static Gson gson = null;


    public CeleryResquest(Call<ResponseBody> call, final CeleryCallback<T> celeryCallback) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response) {
                celeryCallback.onResponse((Call<T>) call,getResponse(response));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                celeryCallback.onFailure((Call<T>) call,t);
            }
        });
    }

    /**
     * gson 解析
     * @param response
     * @return
     */
    private T getResponse(Response<ResponseBody> response){
        T object = null;
        if (gson == null){
            gson = new Gson();
        }
        try {

//            Type mySuperClass = T.getClass().getGenericSuperclass();
//            Type type = ((ParameterizedType)mySuperClass).getActualTypeArguments()[0];

            Type type = new TypeToken<T>() {}.getType();
            String jsonStr = response.body().string();
            object = gson.fromJson(jsonStr, type);
        } catch (IOException e) {
            e.printStackTrace();
         }
        return object;
    }

    private Class<T> className;
    public List<T> fromJsonList(String json) {
        List<T> mList = new ArrayList<T>();
        Gson gson = new Gson();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem, className));
        }
        return mList;
    }

}
