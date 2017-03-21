package com.example.retrofitdemo.mvp.biz;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.retrofitdemo.Config;
import com.example.retrofitdemo.MyApplication;
import com.example.retrofitdemo.http.ApiService;
import com.example.retrofitdemo.mvp.presenter.AlertDialogOnclickListener;
import com.example.retrofitdemo.mvp.presenter.BasePresenter;
import com.example.retrofitdemo.mvp.presenter.SnackbarActionListener;
import com.trycatch.mysnackbar.Prompt;
import com.trycatch.mysnackbar.TSnackbar;
import javax.inject.Inject;

/**
 * Created by Zhou on 2017/3/15.
 * 对BasePresenter定义的接口进行实现
 */
public class BasePresenterCompl implements BasePresenter {

    private Activity activity;
    private AlertDialog.Builder builder;
    private static Toast toast;
    protected ApiService apiService = MyApplication.appComponent.getApiService();
    private ViewGroup viewGroup;
    private  TSnackbar snackBar;

    @Inject
    public BasePresenterCompl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void showAlertDialog(String tittle, String content, final AlertDialogOnclickListener alertDialogOnclickListener) {
        if (builder == null) {
            builder = new AlertDialog.Builder(activity);
        }
        builder.setTitle(tittle)
                .setMessage(content)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface d, int which) {
                                alertDialogOnclickListener.positiveClick(d,which);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface d, int which) {
                                alertDialogOnclickListener.negativeClick(d,which);
                            }
                        })
                .show();
    }

    @Override
    public void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(activity, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    @Override
    public void showSnackbar(int type, String content) {
        if (viewGroup == null) {
            //注意getRootView()最为重要，直接关系到TSnackBar的位置
            viewGroup = (ViewGroup) activity.findViewById(android.R.id.content).getRootView();
        }

        snackBar = TSnackbar.make(viewGroup, "", TSnackbar.LENGTH_INDEFINITE, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
        switch (type){
            case Config.SNACKBAR_SUCCESS:
                snackBar.setPromptThemBackground(Prompt.SUCCESS).setText(content).setDuration(TSnackbar.LENGTH_SHORT).show();
                break;
            case Config.SNACKBAR_WARNING:
                snackBar.setPromptThemBackground(Prompt.WARNING).setText(content).setDuration(TSnackbar.LENGTH_LONG).show();
                break;
            case Config.SNACKBAR_ERROR:
                snackBar.setPromptThemBackground(Prompt.ERROR).setText(content).setDuration(TSnackbar.LENGTH_LONG).show();
                break;
          default:
              break;
        }

    }

    @Override
    public void showSnackbar(int type, String actionName, String content, final SnackbarActionListener actionListener) {
        if (viewGroup == null) {
            //注意getRootView()最为重要，直接关系到TSnackBar的位置
            viewGroup = (ViewGroup) activity.findViewById(android.R.id.content).getRootView();
        }

        snackBar = TSnackbar.make(viewGroup, "", TSnackbar.LENGTH_INDEFINITE, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
        snackBar.setAction(actionName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionListener != null) {
                    actionListener.onClick(snackBar, v);
                }
            }
        });

        switch (type){
            case Config.SNACKBAR_ACTION_SUCCESS:
                snackBar.setPromptThemBackground(Prompt.SUCCESS);
                snackBar.setText(content);
                snackBar.setDuration(TSnackbar.LENGTH_LONG);
                snackBar.show();
                break;
            case Config.SNACKBAR_ACTION_WARNING:
                snackBar.setPromptThemBackground(Prompt.WARNING);
                snackBar.setText(content);
                snackBar.setDuration(TSnackbar.LENGTH_LONG);
                snackBar.show();
                break;
            case Config.SNACKBAR_ACTION_ERROR:
                snackBar.setPromptThemBackground(Prompt.ERROR);
                snackBar.setText(content);
                snackBar.setDuration(TSnackbar.LENGTH_LONG);
                snackBar.show();
                break;
            case Config.SNACKBAR_ACTION_LENGTH_LOADDING_SUCCESS:
                snackBar.setPromptThemBackground(Prompt.SUCCESS);
                snackBar.addIconProgressLoading(0,true,false);
                snackBar.setText(content);
                snackBar.show();
                break;
            case Config.SNACKBAR_ACTION_LENGTH_SUCCESS:
                snackBar.setPromptThemBackground(Prompt.SUCCESS);
                snackBar.setText(content);
                snackBar.show();
                break;
            case Config.SNACKBAR_ACTION_LENGTH_WARNING:
                snackBar.setPromptThemBackground(Prompt.WARNING);
                snackBar.setText(content);
                snackBar.show();
                break;
            case Config.SNACKBAR_ACTION_LENGTH_ERROR:
                snackBar.setPromptThemBackground(Prompt.ERROR);
                snackBar.setText(content);
                snackBar.show();
                break;
            default:
                break;

        }

    }

}
