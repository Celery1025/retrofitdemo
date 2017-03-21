package com.example.retrofitdemo.di.component;

import android.content.Context;

import com.example.retrofitdemo.di.module.AppModule;
import com.example.retrofitdemo.http.ApiService;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by Zhou on 2017/3/15.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context context();  // 提供Applicaiton的Context
    ApiService getApiService();  // 所有Api请求的管理类


//    ThreadExecutor threadExecutor();   // 线程池

//    SpfManager spfManager();  // SharedPreference管理类
//
//    DBManager dbManager();  // 数据库管理类

}
