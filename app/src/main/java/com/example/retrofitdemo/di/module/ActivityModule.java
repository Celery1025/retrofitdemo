package com.example.retrofitdemo.di.module;

import android.app.Activity;

import com.example.retrofitdemo.di.ActivityScope;
import com.example.retrofitdemo.mvp.ivew.IMainAtyView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Zhou on 2017/3/15.
 */

@Module
public class ActivityModule{
    private Activity activity;
    private IMainAtyView iMainAtyView;

    public ActivityModule(Activity activity,IMainAtyView iMainAtyView) {
        this.activity = activity;
        this.iMainAtyView = iMainAtyView;
    }

    @Provides
    @ActivityScope
    Activity getActivity(){
        return activity;
    }

    @Provides
    @ActivityScope
    IMainAtyView getiMainAtyView(){
        return iMainAtyView;
    }

}
