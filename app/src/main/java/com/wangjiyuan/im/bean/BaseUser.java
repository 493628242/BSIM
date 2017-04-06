package com.wangjiyuan.im.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wjy on 2017/2/17.
 */

public class BaseUser implements Parcelable {
    @SerializedName("phonenumber")
    protected String phonenumber; //电话号码
    @SerializedName("password")
    protected String password;  //密码

    public BaseUser() {
    }

    public BaseUser(Parcel in) {
        phonenumber = in.readString();
        password = in.readString();
    }

    public static final Creator<BaseUser> CREATOR = new Creator<BaseUser>() {
        @Override
        public BaseUser createFromParcel(Parcel in) {
            return new BaseUser(in);
        }

        @Override
        public BaseUser[] newArray(int size) {
            return new BaseUser[size];
        }
    };

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phonenumber);
        dest.writeString(password);
    }
}
