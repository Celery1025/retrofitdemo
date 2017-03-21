package com.example.retrofitdemo.di.component;


import com.example.retrofitdemo.aty.MainActivity;
import com.example.retrofitdemo.di.ActivityScope;
import com.example.retrofitdemo.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by Zhou on 2017/3/15.
 */
@ActivityScope
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
