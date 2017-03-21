package com.example.retrofitdemo.aty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.retrofitdemo.MyApplication;
import com.example.retrofitdemo.http.ApiService;
import okhttp3.MediaType;

/**
 * Created by xieshuilin on 2017/2/17.
 */

public class BaseActivity extends AppCompatActivity {
    public static final MediaType content_type = okhttp3.MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
