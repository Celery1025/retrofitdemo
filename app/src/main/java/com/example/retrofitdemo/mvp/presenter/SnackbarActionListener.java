package com.example.retrofitdemo.mvp.presenter;

import android.view.View;

import com.trycatch.mysnackbar.TSnackbar;

/**
 * Created by Zhou on 2017/3/16.
 * SnackbarAction 监听接口
 */
public interface SnackbarActionListener {
    void onClick(TSnackbar snackBar,View view);
}
