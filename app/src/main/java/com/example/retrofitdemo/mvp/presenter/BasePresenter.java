package com.example.retrofitdemo.mvp.presenter;


/**
 * Created by Zhou on 2017/3/15.
 * 基础Presenter接口方法定义基础方法
 */
public interface BasePresenter {

    /*********************************************【提示通用方法 Start】******************************/
    /**
     * 弹出对话框
     * @param tittle 标题
     * @param content 提示信息
     * @param alertDialogOnclickListener 取消 和 确认 接口监听
     */
    void showAlertDialog(String tittle,String content,AlertDialogOnclickListener alertDialogOnclickListener);

    /**
     * 弹出提示信息
     * @param content 提示内容
     */
    void showToast(String content);

    /**
     * 展示Snackbar提示
     * @param type 提示类型 成功、警告、错误
     * @param content 提示内容
     */
    void showSnackbar(int type,String content);

    /**
     *  展示Snackbar提示并带有Action
     * @param type 类型
     * @param actionName action名称
     * @param content 提示内容
     * @param actionListener 监听接口
     */
    void showSnackbar(int type,String actionName,String content,SnackbarActionListener actionListener);

    /*********************************************【提示通用方法 End】******************************/



}
