package com.wangjiyuan.im.activity.usermesg;

import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.utils.HttpUtils;

import rx.Observable;

/**
 * Created by wjy on 2017/4/8.
 */

public class UserMesgModel implements IUserMesgContract.IUserMesgModel {
    @Override
    public Observable<Friend> getFriend(String phonenumber) {
        return HttpUtils.getInstance().getHttpInterfaces().UserMesg(phonenumber);
    }
}
