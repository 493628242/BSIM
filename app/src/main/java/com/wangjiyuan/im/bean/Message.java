package com.wangjiyuan.im.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wjy on 2017/2/22.
 */

public class Message implements Parcelable {


    @SerializedName("content")
    private String content;
    @SerializedName("time")
    private Long time;
    @SerializedName("form")
    private Friend form;
    @SerializedName("to")
    private Friend to;
    @SerializedName("type")
    private int type;//类型 1为文本，0为图片,2验证消息

    public static final int TEXT = 1;
    public static final int IMAGE = 0;
    public static final int CODE = 2;

    public Message() {

    }

    protected Message(Parcel in) {
        content = in.readString();
        form = in.readParcelable(Friend.class.getClassLoader());
        to = in.readParcelable(Friend.class.getClassLoader());
        type = in.readInt();
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


    public Friend getForm() {
        return form;
    }

    public void setForm(Friend form) {
        this.form = form;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Friend getTo() {
        return to;
    }

    public void setTo(Friend to) {
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
        dest.writeParcelable(form, flags);
        dest.writeParcelable(to, flags);
        dest.writeInt(type);
    }


    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", time=" + time +
                ", form=" + form +
                ", to=" + to +
                ", type=" + type +
                '}';
    }
}
