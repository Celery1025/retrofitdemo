package com.example.retrofitdemo.di.module;

import android.content.Context;

import com.example.retrofitdemo.http.ApiService;
import com.example.retrofitdemo.http.RetrofitClientUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zhou on 2017/3/15.
 */

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    ApiService provideApiService(){
        return RetrofitClientUtil.getService(context);
    }

}
