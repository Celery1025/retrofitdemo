package com.example.retrofitdemo.mvp.biz;

import android.app.Activity;
import android.util.Log;

import com.example.retrofitdemo.Config;
import com.example.retrofitdemo.http.DownLoadProgressListener;
import com.example.retrofitdemo.http.OkhttpClientUtils;
import com.example.retrofitdemo.model.DateTime;
import com.example.retrofitdemo.model.TimeInfo;
import com.example.retrofitdemo.mvp.ivew.IMainAtyView;
import com.example.retrofitdemo.mvp.presenter.IMainAtyPresenter;
import java.util.HashMap;

import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zhou on 2017/3/15.
 */
public class MainAtyPresenterCompl extends  BasePresenterCompl implements IMainAtyPresenter {

    private IMainAtyView iMainAtyView;
    private Activity activity;

    @Inject
    public MainAtyPresenterCompl(Activity activity,IMainAtyView iMainAtyView) {
        super(activity);
        this.activity = activity;
        this.iMainAtyView = iMainAtyView;
    }

    @Override
    public void doGetRetrofitData() {
        apiService.getRetrofitData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<DateTime>() {
                    @Override
                    public void onNext(DateTime value) {
                        iMainAtyView.backRetrofitData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError","*************");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete","*************");
                    }
                });
    }

    @Override
    public void doPostForm(HashMap<String, String> map) {
        Call<TimeInfo> call1 = apiService.getTimeInfoPostForm(map);
        call1.enqueue(new Callback<TimeInfo>() {
            @Override
            public void onResponse(Call<TimeInfo> call, Response<TimeInfo> response) {
                iMainAtyView.backPostData(response.body());
            }

            @Override
            public void onFailure(Call<TimeInfo> call, Throwable t) {

            }
        });

    }

    @Override
    public void doList() {
//        List<String> list = new ArrayList<>();
//        for (int i=0;i<10000;i++) {
//            list.add(i+"");
//        }
//        iMainAtyView.backList(list);
    }

    @Override
    public void downFile(String url) {
        final byte[] bytes = new byte[0];
        new OkhttpClientUtils().downLoad(activity, url, new DownLoadProgressListener() {
            @Override
            public void onLoading(long current, long total, boolean done) {
                current += current;
                iMainAtyView.backDownFile(((float)current/total)*100,bytes);
            }
            @Override
            public void onSuccess(byte[] bytes) {
                iMainAtyView.backDownFile(100,bytes);
            }

            @Override
            public void onFailure(int code, String Msg, Exception e) {
                if (code != 200){
                    showSnackbar(Config.SNACKBAR_ERROR,Msg);
                }
            }
        });
    }

}
