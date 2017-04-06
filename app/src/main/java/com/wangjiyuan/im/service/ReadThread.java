package com.wangjiyuan.im.service;

import android.os.Bundle;
import android.os.Message;

import com.google.gson.Gson;
import com.wangjiyuan.im.base.BaseApplication;
import com.wangjiyuan.im.handler.ChatMessageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wjy on 2017/4/1.
 * 管理接收消息的线程
 */

public class ReadThread extends Thread {
    private BufferedReader readMessage;
    private String Message;
    private ChatMessageHandler chatMessageHandler;
    private Gson gson;

    public ReadThread(BufferedReader readMessage) {
        this.readMessage = readMessage;
        chatMessageHandler = BaseApplication.getChatMessageHandler();
        gson = new Gson();

    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                Message = readMessage.readLine();
                android.os.Message message = chatMessageHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putParcelable(ChatMessageHandler.MESSAGE, gson.fromJson(Message, com.wangjiyuan.im.bean.Message.class));
                message.setData(bundle);
                chatMessageHandler.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
