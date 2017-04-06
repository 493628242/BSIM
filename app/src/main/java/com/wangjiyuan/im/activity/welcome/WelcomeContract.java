package com.wangjiyuan.im.activity.welcome;

import com.wangjiyuan.im.contract.IBaseModel;
import com.wangjiyuan.im.contract.IBasePresenter;

/**
 * Created by wjy on 2017/2/2.
 */

public class WelcomeContract {

    /**
     * 欢迎页面的作用：
     * <p>
     * 判断是否该已经有过登录账户，是则直接进行登录，否则及跳转到登录界面
     */
    interface WelcomePresenter extends IBasePresenter {

    }

    interface WelcomeModel extends IBaseModel {

    }
}
