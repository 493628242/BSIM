package com.wangjiyuan.im.activity.login;

import android.util.Log;

import com.wangjiyuan.im.bean.User;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wjy on 2017/3/28.
 */

public class LoginPresenter implements ILoginContract.ILoginPresenter {
    private ILoginContract.ILoginView v;
    private ILoginContract.ILoginModel model;
    private Subscription subscribe;

    public LoginPresenter(ILoginContract.ILoginView v) {
        model = new LoginModel();
        this.v = v;
    }

    @Override
    public void getUserMessage(String phonenumber, String password) {


        subscribe = model.getUser(phonenumber, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        subscribe.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                        v.LoginFailure();
                        subscribe.unsubscribe();
                    }

                    @Override
                    public void onNext(User user) {
                        v.LoginSuccess(user);

                    }
                });
    }
}
