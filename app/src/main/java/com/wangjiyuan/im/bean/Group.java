package com.wangjiyuan.im.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by wjy on 2017/3/11.
 */

public class Group implements Parcelable{
    private String title;
    private int isExpand;//代表是否展开 0代表：未展开 1代表：已展开
    private ArrayList<Friend> childs;
    private int childsize;

    public Group() {
    }

    protected Group(Parcel in) {
        title = in.readString();
        isExpand = in.readInt();
        childs = in.createTypedArrayList(Friend.CREATOR);
        childsize = in.readInt();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public int getChildsize() {
        return childsize;
    }

    public ArrayList<Friend> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Friend> childs) {
        this.childs = childs;
    }

    public int getIsExpand() {
        return isExpand;
    }

    public void setIsExpand(int isExpand) {
        this.isExpand = isExpand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(isExpand);
        dest.writeTypedList(childs);
        dest.writeInt(childsize);
    }
}
