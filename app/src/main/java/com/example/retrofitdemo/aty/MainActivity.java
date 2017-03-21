package com.example.retrofitdemo.aty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.retrofitdemo.Config;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.di.component.DaggerActivityComponent;
import com.example.retrofitdemo.model.DateTime;
import com.example.retrofitdemo.model.TimeInfo;
import com.example.retrofitdemo.mvp.ivew.IMainAtyView;
import com.example.retrofitdemo.mvp.biz.MainAtyPresenterCompl;
import com.example.retrofitdemo.di.module.ActivityModule;
import com.example.retrofitdemo.mvp.presenter.AlertDialogOnclickListener;
import com.example.retrofitdemo.widget.GlideCircleTransform;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MainActivity extends BaseActivity implements IMainAtyView{

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.my_image_view)
    ImageView image;
    @BindView(R.id.caijian)
    ImageView caijian;
    @BindView(R.id.yuanjiao)
    ImageView yuanjiao;
    @BindView(R.id.huidu)
    ImageView huidu;

    @Inject
    MainAtyPresenterCompl mainAtyPresenterCompl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 构建Component并注入
       DaggerActivityComponent.builder().activityModule(new ActivityModule(this,this)).build().inject(this);

        Glide.with(this).load("http://upload-images.jianshu.io/upload_images/268450-c3d6a9d309006182.gif?imageMogr2/auto-orient/strip").into(image);

        //圆形裁剪
        Glide.with(this)
                .load("http://upload-images.jianshu.io/upload_images/268450-c3d6a9d309006182.gif?imageMogr2/auto-orient/strip")
                .bitmapTransform(new GlideCircleTransform(this,1,0xffe60000))
                .into(caijian);

        //圆角处理
        Glide.with(this)
                .load("http://upload-images.jianshu.io/upload_images/268450-c3d6a9d309006182.gif?imageMogr2/auto-orient/strip")
                .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
                .into(yuanjiao);

        //灰度处理
        Glide.with(this)
                .load("http://upload-images.jianshu.io/upload_images/268450-c3d6a9d309006182.gif?imageMogr2/auto-orient/strip")
                .bitmapTransform(new GrayscaleTransformation(this))
                .into(huidu);
    }

    @Override
    public void backRetrofitData(DateTime value) {
        Log.e("backRetrofitData",value.getResult().getArea_style_areanm());
    }

    @Override
    public void backPostData(TimeInfo timeInfo) {
        Log.e("backPostData",timeInfo.getResult().toString());
    }

    @Override
    public void backList(List<String> list) {

    }

    @Override
    public void backDownFile(float percent, byte[] bytes) {
        if (percent<100) {
            Log.e("下载进度", percent + "%");
        }else {
            mainAtyPresenterCompl.showSnackbar(Config.SNACKBAR_SUCCESS,"下载完成");
        }
    }


    @OnClick({R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8})
        void OnClick(View view){
        switch (view.getId()){
            case R.id.button1:
                mainAtyPresenterCompl.doGetRetrofitData();
                break;
            case R.id.button2:
                HashMap<String,String> map = new HashMap<>();
                map.put("app","life.time");
                map.put("appkey","10003");
                map.put("sign","b59bc3ef6191eb9f747dd4e83c99f2a4");
                map.put("format","json");
                mainAtyPresenterCompl.doPostForm(map);
                break;
            case R.id.button3:

                break;
            case R.id.button4://上传

                break;
            case R.id.button5://下载
                mainAtyPresenterCompl.downFile("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490000794451&di=7d2c732b21765417c8d7ad896f169a5e&imgtype=0&src=http%3A%2F%2Fpic17.nipic.com%2F20111015%2F4695500_224140299162_2.jpg");
                break;
            case R.id.button6:
                mainAtyPresenterCompl.showToast("Toast 提示信息");
                break;
            case R.id.button7:
                mainAtyPresenterCompl.showAlertDialog("提示", "确定要退出？", new AlertDialogOnclickListener() {
                    @Override
                    public void positiveClick(DialogInterface d, int which) {

                    }

                    @Override
                    public void negativeClick(DialogInterface d, int which) {

                    }
                });
                break;
            case R.id.button8:
                mainAtyPresenterCompl.showSnackbar(Config.SNACKBAR_ACTION_LENGTH_LOADDING_SUCCESS,"取消","正在加载...",null);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainAtyPresenterCompl.showSnackbar(Config.SNACKBAR_SUCCESS,"加载成功");
                    }
                },2000);
                break;
        }

    }



}
