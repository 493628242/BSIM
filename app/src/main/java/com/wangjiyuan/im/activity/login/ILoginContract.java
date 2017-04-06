package com.wangjiyuan.im.activity.login;

import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.contract.IBaseModel;
import com.wangjiyuan.im.contract.IBasePresenter;
import com.wangjiyuan.im.contract.IBaseView;

/**
 * Created by wjy on 2017/2/20.
 */

public interface ILoginContract {
    interface ILoginModle extends IBaseModel {
        void getUserMessage(User user);
    }

    interface ILoginPresenter extends IBasePresenter {
        User getUserMessage(String phonenumber, String password);
    }

    interface ILoginView extends IBaseView {
        void LoginSuccess();

        void LoginFailure();
    }
}
