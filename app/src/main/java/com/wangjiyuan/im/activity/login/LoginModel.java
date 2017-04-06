package com.wangjiyuan.im.activity.login;

import com.wangjiyuan.im.base.BaseApplication;
import com.wangjiyuan.im.bean.User;

/**
 * Created by wjy on 2017/3/28.
 */

public class LoginModel implements ILoginContract.ILoginModle {
    @Override
    public void getUserMessage(User user) {
        BaseApplication.setUser(user);


    }
}
