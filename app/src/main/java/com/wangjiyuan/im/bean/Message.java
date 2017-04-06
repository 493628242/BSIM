package com.wangjiyuan.im.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wjy on 2017/2/22.
 */

public class Message implements Parcelable {


    private String content;
    private Long time;
    private String formnickname;
    private String tonickname;
    private String form;
    private String to;
    private int type;//类型 1为文本，0为图片

    public static final int TEXT = 1;
    public static final int IMAGE = 0;

    public Message() {

    }

    protected Message(Parcel in) {
        content = in.readString();
        form = in.readString();
        to = in.readString();
        type = in.readInt();
        formnickname = in.readString();
        tonickname = in.readString();
    }

    public String getFormnickname() {
        return formnickname;
    }

    public void setFormnickname(String formnickname) {
        this.formnickname = formnickname;
    }

    public String getTonickname() {
        return tonickname;
    }

    public void setTonickname(String tonickname) {
        this.tonickname = tonickname;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(form);
        dest.writeString(to);
        dest.writeInt(type);
        dest.writeString(formnickname);
        dest.writeString(tonickname);
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", time=" + time +
                ", form='" + form + '\'' +
                ", to='" + to + '\'' +
                ", tonickname='" + tonickname + '\'' +
                ", formnickname='" + formnickname + '\'' +
                ", type=" + type +
                '}';
    }
}
