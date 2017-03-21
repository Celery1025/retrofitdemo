package com.example.retrofitdemo.mvp.presenter;

import android.content.DialogInterface;

/**
 * Created by Zhou on 2017/3/16.
 * AlertDialog 回调接口定义
 */
public interface AlertDialogOnclickListener {
    void positiveClick(DialogInterface d, int which);
    void negativeClick(DialogInterface d, int which);
}
