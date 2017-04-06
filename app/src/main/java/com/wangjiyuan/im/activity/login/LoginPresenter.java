package com.wangjiyuan.im.activity.login;

import android.content.Context;
import android.util.Log;

import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.config.SharedConfig;
import com.wangjiyuan.im.http.HttpInterfaces;
import com.wangjiyuan.im.utils.HttpUtils;
import com.wangjiyuan.im.utils.SharedPreferenceUtil;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wjy on 2017/3/28.
 */

public class LoginPresenter implements ILoginContract.ILoginPresenter {
    private ILoginContract.ILoginView v;
    private ILoginContract.ILoginModle modle;

    public LoginPresenter(ILoginContract.ILoginView v) {
        modle = new LoginModel();
        this.v = v;
    }

    @Override
    public User getUserMessage(String phonenumber, String password) {
        HttpUtils instance = HttpUtils.getInstance();
        HttpInterfaces httpInterfaces = instance.getHttpInterfaces();
        Observable<User> login = httpInterfaces.Login(phonenumber, password);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        v.LoginSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                        v.LoginFailure();
                    }

                    @Override
                    public void onNext(User user) {
                        modle.getUserMessage(user);
                        SharedPreferenceUtil.putString((Context) v, SharedConfig.PHONE_NUBER, user.getPhonenumber());
                        SharedPreferenceUtil.putString((Context) v, SharedConfig.TOKEN, user.getToken());
                    }
                });
        return null;
    }
}
