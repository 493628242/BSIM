package com.wangjiyuan.im.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ChatService extends Service {
    private Socket chatScoket;
    public static final String HEART_BEAT_CODE = "200";
    private BufferedReader readMessage;
    private BufferedWriter writeMessage;
    public static final int PORT = 8889;
    public static final String ID_ADDRESS = "192.168.1.102";
    private Timer timer;
    private TimerTask timerTask;

    int i = 0;

    public ChatService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            chatScoket = new Socket(ID_ADDRESS, PORT);
            readMessage = new BufferedReader(new InputStreamReader(chatScoket.getInputStream()));
            writeMessage = new BufferedWriter(new OutputStreamWriter(chatScoket.getOutputStream()));
            StartThread(readMessage, writeMessage);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void StartThread(BufferedReader readMessage, BufferedWriter writeMessage) {
        ReadThread readThread = new ReadThread(readMessage);
        WriteThread writeThread = new WriteThread(writeMessage);
        readThread.start();
        writeThread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        timer = new Timer(true);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    writeMessage.write(HEART_BEAT_CODE);
                    writeMessage.newLine();
                    writeMessage.flush();
                    Log.e("timer", i++ + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask, 20000, 20000); //每20000毫秒发送一次心跳包
        return new ChatBinder();
    }

    class ChatBinder extends Binder {
        public ChatService geteService() {
            return ChatService.this;
        }
    }
}
