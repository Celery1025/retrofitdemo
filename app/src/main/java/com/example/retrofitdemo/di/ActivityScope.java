package com.example.retrofitdemo.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Zhou on 2017/3/15.
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {}
