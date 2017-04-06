package com.wangjiyuan.im.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wjy on 2017/3/4.
 */

public class FriendWithMesg implements Parcelable, Comparable<FriendWithMesg> {
    private String LastChatMesg; //最新的消息
    private String nickname;    //昵称
    private int sex;    //性别
    private String headimage;   //头像
    private String phonenumber; //电话号码
    private Long time;

    public FriendWithMesg() {
    }

    protected FriendWithMesg(Parcel in) {
        LastChatMesg = in.readString();
        nickname = in.readString();
        sex = in.readInt();
        headimage = in.readString();
        phonenumber = in.readString();
        time = in.readLong();
    }

    public static final Creator<FriendWithMesg> CREATOR = new Creator<FriendWithMesg>() {
        @Override
        public FriendWithMesg createFromParcel(Parcel in) {
            return new FriendWithMesg(in);
        }

        @Override
        public FriendWithMesg[] newArray(int size) {
            return new FriendWithMesg[size];
        }
    };

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public String getLastChatMesg() {
        return LastChatMesg;
    }

    public void setLastChatMesg(String lastChatMesg) {
        LastChatMesg = lastChatMesg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "FriendWithMesg{" +
                "headimage='" + headimage + '\'' +
                ", LastChatMesg='" + LastChatMesg + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", phonenumber='" + phonenumber + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(LastChatMesg);
        dest.writeString(nickname);
        dest.writeInt(sex);
        dest.writeString(headimage);
        dest.writeString(phonenumber);
        dest.writeLong(time);
    }

    @Override
    public int compareTo(FriendWithMesg o) {
        return (int) (o.time - this.time);
    }
}

