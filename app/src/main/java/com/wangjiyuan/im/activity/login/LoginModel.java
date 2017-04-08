package com.wangjiyuan.im.activity.login;

import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.http.HttpInterfaces;
import com.wangjiyuan.im.utils.HttpUtils;

import rx.Observable;

/**
 * Created by wjy on 2017/3/28.
 */

public class LoginModel implements ILoginContract.ILoginModel {
    @Override
    public Observable<User> getUser(String phonenumber, String password) {
        HttpUtils instance = HttpUtils.getInstance();
        HttpInterfaces httpInterfaces = instance.getHttpInterfaces();
        return httpInterfaces.Login(phonenumber, password);

    }
}
