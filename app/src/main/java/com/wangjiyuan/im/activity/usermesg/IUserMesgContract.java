package com.wangjiyuan.im.activity.usermesg;

import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.contract.IBasePresenter;
import com.wangjiyuan.im.contract.IBaseView;

import rx.Observable;

/**
 * Created by wjy on 2017/4/8.
 */

public interface IUserMesgContract {
    interface IUserMesgModel {
        Observable<Friend> getFriend(String phonenumber);
    }

    interface IUserMesgPresenter extends IBasePresenter {
        void getFriend(String phonenumber);
    }

    interface IUserMesgView extends IBaseView {
        void LoginSuccess(Friend friend);

        void LoginFailure();
    }
}
