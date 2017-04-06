package com.wangjiyuan.im.service;

import com.google.gson.Gson;
import com.wangjiyuan.im.base.BaseApplication;
import com.wangjiyuan.im.bean.Message;
import com.wangjiyuan.im.handler.ChatMessageHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wjy on 2017/4/4.
 * 管理发送的线程
 */

public class WriteThread extends Thread {

    private BufferedWriter writeMessage;
    private String Message;
    private ChatMessageHandler chatMessageHandler;
    private Gson gson;
    private ArrayList<com.wangjiyuan.im.bean.Message> messages;
    private Long LastSendTime;

    public WriteThread(BufferedWriter writeMessage) {
        this.writeMessage = writeMessage;
        chatMessageHandler = BaseApplication.getChatMessageHandler();
        gson = new Gson();
        messages = new ArrayList<>();

    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                for (Message message :
                        messages) {
                    LastSendTime = System.currentTimeMillis();
                    Message = gson.toJson(message);
                    writeMessage.write(Message);
                    writeMessage.newLine();
                    writeMessage.flush();
                }
                if (LastSendTime % 20000 == 0) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
