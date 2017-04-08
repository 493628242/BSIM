package com.wangjiyuan.im.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.bean.Group;
import com.wangjiyuan.im.activity.main.MainActivity;
import com.wangjiyuan.im.activity.usermesg.UserMesgActivity;
import com.wangjiyuan.im.utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wjy on 2017/3/11.
 */

public class FriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Object> expandable;

    public FriendAdapter(Context context, ArrayList<Group> groups) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        expandable = new ArrayList<>();
        expandable.addAll(groups);
    }

    @Override
    public int getItemViewType(int position) {
        if (expandable.get(position) instanceof Friend) {
            return R.layout.friend_item_child;
        } else {
            return R.layout.friend_item_group;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent, false);
        switch (viewType) {
            case R.layout.friend_item_child:
                return new ChildViewHolder(view);
            case R.layout.friend_item_group:
                return new GroupViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object o = expandable.get(position);
        if (holder != null) {
            if (holder instanceof ChildViewHolder) {
                Friend friend = (Friend) o;
                ChildViewHolder childViewHolder = (ChildViewHolder) holder;
                childViewHolder.nickName.setText(friend.getNickname());
                GlideUtils.loadCircleImage(context, friend.getHeadimage(), childViewHolder.headImage, R.mipmap.ic_launcher);
            } else {
                Group group = (Group) o;
                GroupViewHolder groupViewHolder = (GroupViewHolder) holder;
                groupViewHolder.groupName.setText(group.getTitle());
            }
        }
    }

    @Override
    public int getItemCount() {
        return expandable.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.group_name)
        TextView groupName;

        public GroupViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) { //点击展开和收起子item
            Group group = (Group) expandable.get(getAdapterPosition());
            if (group.getIsExpand() == 0) {
                ObjectAnimator.ofFloat(icon, "rotation", 0f, 90f).start();
                expandable.addAll(getAdapterPosition() + 1, group.getChilds());
                group.setIsExpand(1);
            } else {
                ObjectAnimator.ofFloat(icon, "rotation", 90f, 0f).start();
                expandable.removeAll(group.getChilds());
                group.setIsExpand(0);
            }
            FriendAdapter.this.notifyDataSetChanged();
        }
    }

    class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.head_image)
        ImageView headImage;
        @BindView(R.id.nick_name)
        TextView nickName;

        public ChildViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //TODO 页面跳转
            MainActivity activity = (MainActivity) FriendAdapter.this.context;
            Bundle bundle = new Bundle();
            bundle.putString(UserMesgActivity.PHONE_NUMBER, ((Friend)expandable.get(getAdapterPosition())).getPhonenumber());
            activity.openActivity(UserMesgActivity.class,bundle);
        }
    }


}
