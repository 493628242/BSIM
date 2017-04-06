package com.wangjiyuan.im.handler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.activity.chat.ChatActivity;
import com.wangjiyuan.im.activity.main.MainActivity;
import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.config.UrlConfig;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

/**
 * Created by wjy on 2017/4/1.
 * 此对象的创建时间应该在账号登录成功后
 * 也就是MainActivity显示在屏幕上后创建
 * <p>
 * 接收到消息后需要通知2个地方
 * 1、发出Notification
 * 2、通知消息页面改变数据
 */

public class ChatMessageHandler extends Handler {
    public static final String MESSAGE = "message";
    public static final String MESSAGES = "messages";
    private Context context;
    private NotificationManager manager;
    private int messageNumber = 0;
    private com.wangjiyuan.im.bean.Message message;
    private ArrayList<com.wangjiyuan.im.bean.Message> messages;
    private SoftReference<ChatActivity> chatActivitySoftReference;
    private SoftReference<MainActivity> mainActivitySoftReference;

    public ChatMessageHandler(Context context) {
        this.context = context;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        messages = new ArrayList<>();
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Bundle data = msg.getData();
        message = data.getParcelable(MESSAGE);
        messages.add(message);
        messageNumber = messages.size();
        if (chatActivitySoftReference.get() == null
                || !message.getForm().equals(chatActivitySoftReference.get().getFriend().getPhonenumber())) {
            showNotification();
        } else if (chatActivitySoftReference.get() != null) {
            chatActivitySoftReference.get().onReceiveMessageListener(messages);
        }
        if (mainActivitySoftReference.get() != null) {
        }


    }

    public void setChatActivity(ChatActivity chatActivity) {
        this.chatActivitySoftReference = new SoftReference<>(chatActivity);
    }

    public void setMainActivitySoftReference(MainActivity mainActivity) {
        mainActivitySoftReference = new SoftReference<>(mainActivity);
    }




    /**
     * 显示通知
     */
    private void showNotification() {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        Notification.Builder builder = new Notification.Builder(context);
        Notification build = builder.setLargeIcon(icon)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageNumber == 1 ? message.getFormnickname() : "通知")
                .setSubText(messageNumber == 0 ? (message.getType() == 1 ? message.getContent() : "[图片]") : "未读消息:" + messageNumber + "条")
                .setWhen(message.getTime())
                .setContentIntent(getPendIntent())
                .build();
        build.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(message.getForm(), 1, build);
    }

    /**
     * 点击通知后的意图
     *
     * @return
     */
    private PendingIntent getPendIntent() {
        if (messageNumber == 1) {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putParcelableArrayListExtra(MESSAGES, messages);
            Friend friend = new Friend();
            friend.setHeadimage(UrlConfig.BASE_URL + UrlConfig.HEAD_IMAGE + message.getForm() + ".jpg");
            friend.setPhonenumber(message.getForm());
            friend.setNickname(message.getFormnickname());
            intent.putExtra(ChatActivity.FRIEND, friend);
            return PendingIntent.getActivity(context, 200, intent, PendingIntent.FLAG_ONE_SHOT);
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putParcelableArrayListExtra(MESSAGES, messages);
            return PendingIntent.getActivity(context, 200, intent, PendingIntent.FLAG_ONE_SHOT);
        }
    }

    public interface onReceiveMessageListener {
        void onReceiveMessageListener(ArrayList<com.wangjiyuan.im.bean.Message> messages);
    }
}
