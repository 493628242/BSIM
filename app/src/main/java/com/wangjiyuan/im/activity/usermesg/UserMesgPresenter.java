package com.wangjiyuan.im.activity.usermesg;

import com.wangjiyuan.im.bean.Friend;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wjy on 2017/4/8.
 */

public class UserMesgPresenter implements IUserMesgContract.IUserMesgPresenter {
    private IUserMesgContract.IUserMesgView view;
    private IUserMesgContract.IUserMesgModel model;
    private Subscription subscribe;

    public UserMesgPresenter(IUserMesgContract.IUserMesgView view) {
        this.view = view;
        model = new UserMesgModel();
    }

    @Override
    public void getFriend(String phonenumber) {
        subscribe = model.getFriend(phonenumber).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Friend>() {
                    @Override
                    public void onCompleted() {
                        subscribe.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscribe.unsubscribe();
                    }

                    @Override
                    public void onNext(Friend friend) {
                        view.LoginSuccess(friend);
                    }
                });

    }
}
