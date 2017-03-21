package com.example.retrofitdemo.mvp.ivew;

import com.example.retrofitdemo.model.DateTime;
import com.example.retrofitdemo.model.TimeInfo;
import java.util.List;

/**
 * Created by Zhou on 2017/3/15.
 */

public interface IMainAtyView {
    void backRetrofitData(DateTime value);
    void backPostData(TimeInfo timeInfo);
    void backList(List<String> list);
    void backDownFile(float percent,byte[] bytes);
}
