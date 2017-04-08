package com.wangjiyuan.im.activity.login;

import com.wangjiyuan.im.contract.IBaseModel;
import com.wangjiyuan.im.contract.IBasePresenter;
import com.wangjiyuan.im.contract.IBaseView;
import com.wangjiyuan.im.bean.User;

import rx.Observable;

/**
 * Created by wjy on 2017/2/20.
 */

public interface ILoginContract {
    interface ILoginModel extends IBaseModel {
        Observable<User> getUser(String phonenumber, String password);
    }

    interface ILoginPresenter extends IBasePresenter {
        void getUserMessage(String phonenumber, String password);
    }

    interface ILoginView extends IBaseView {
        void LoginSuccess(User user);

        void LoginFailure();
    }
}
