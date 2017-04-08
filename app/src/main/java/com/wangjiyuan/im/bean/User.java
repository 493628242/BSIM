package com.wangjiyuan.im.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wjy on 2017/2/5.
 */

public class User extends BaseUser implements Parcelable {
    @SerializedName("nickname")
    private String nickname;    //昵称
    @SerializedName("sex")
    private int sex;    //性别
    @SerializedName("headimage")
    private String headimage;   //头像
    @SerializedName("token")
    private String token; //用于服务器验证身份

    public User() {
        super();

    }

    protected User(Parcel in) {
        super(in);
        nickname = in.readString();
        sex = in.readInt();
        headimage = in.readString();
        token = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }

    };

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadimage() {
        return headimage;
    }

    public String getNickname() {
        return nickname;
    }


    public int getSex() {
        return sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "phonenumber='" + phonenumber + '\'' +
                ", password='" + password + '\'' +
                ", headimage='" + headimage + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex + '\'' +
                ", token=" + token +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(nickname);
        dest.writeInt(sex);
        dest.writeString(headimage);
        dest.writeString(token);
    }

    public Friend changToFriend() {
        Friend friend = new Friend();
        friend.setHeadimage(headimage);
        friend.setNickname(nickname);
        friend.setPhonenumber(phonenumber);
        friend.setSex(sex);
        return friend;
    }
}
