package com.example.retrofitdemo.mvp.presenter;


import java.util.HashMap;

/**
 * Created by xieshuilin on 2017/2/16.
 */

public interface IMainAtyPresenter extends BasePresenter{
    /**
     * get请求
     */
    void doGetRetrofitData();

    /**
     * post请求
     * @param map
     */
    void doPostForm(HashMap<String,String> map);

    /**
     * 获取列表
     */
    void doList();

    /**
     * 文件下载
     * @param url
     */
    void downFile(String url);
}
