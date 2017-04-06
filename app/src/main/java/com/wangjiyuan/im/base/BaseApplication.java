package com.wangjiyuan.im.base;

import android.app.Application;

import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.handler.ChatMessageHandler;

/**
 * Created by wjy on 2017/2/1.
 */

public class BaseApplication extends Application {
    public static BaseApplication APP;
    private static int widthPixels;
    private static int heightPixels;
    private static float density;
    private static User user;
    private static ChatMessageHandler chatMessageHandler = null;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        BaseApplication.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        widthPixels = getResources().getDisplayMetrics().widthPixels;
        heightPixels = getResources().getDisplayMetrics().heightPixels;
        density = getResources().getDisplayMetrics().density;
        APP = this;
        //测试用
        user = new User();
        user.setSex(1);
        user.setPhonenumber("231543212");
        user.setNickname("张三");
        user.setHeadimage("http://diy.qqjay.com/u2/2012/0913/55a80cdb4fca56c77c12a38c079bf6ab.jpg");
    }

    public static float getDensity() {
        return density;
    }

    public static int getHeightPixels() {
        return heightPixels;
    }

    public static int getWidthPixels() {
        return widthPixels;
    }

    public static ChatMessageHandler getChatMessageHandler() {
        return chatMessageHandler;
    }

    public void setChatMessageHandler(ChatMessageHandler chatMessageHandler) {
        this.chatMessageHandler = chatMessageHandler;
    }
}
