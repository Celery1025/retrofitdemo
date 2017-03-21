package com.example.retrofitdemo;

import android.app.Application;
import android.content.Context;

import com.example.retrofitdemo.di.component.AppComponent;
import com.example.retrofitdemo.di.component.DaggerAppComponent;
import com.example.retrofitdemo.di.module.AppModule;

/**
 * Created by xieshuilin on 2017/2/16.
 */
public class MyApplication extends Application {

    public static AppComponent appComponent = null;
    public static Context ApplicationContext = null;


    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContext = this;
        initAppComponent();
//        initLeakCanary();
//        initBlockCanary();
    }

    /**
     * 构建AppComponent并注入
     * 提供一些工具类数据和通用接口
     * @return
     */
    public AppComponent initAppComponent(){
        if (appComponent == null) {
            appComponent =  DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

//    /**
//     * 初始化内存泄露工具
//     */
//    private void initLeakCanary(){
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//    }
//
//    /**
//     * 初始化性能测试工具
//     */
//    private void initBlockCanary(){
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();
//    }

}
