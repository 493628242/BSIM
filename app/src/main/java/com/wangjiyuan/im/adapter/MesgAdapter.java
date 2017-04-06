package com.wangjiyuan.im.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.bean.FriendWithMesg;
import com.wangjiyuan.im.activity.chat.ChatActivity;
import com.wangjiyuan.im.activity.main.MainActivity;
import com.wangjiyuan.im.utils.GlideUtils;

import java.util.Iterator;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wjy on 2017/3/4.
 * <p>
 * 用于显示消息的适配器 {@link com.wangjiyuan.im.fragment.message.MesgFragment}
 */

public class MesgAdapter extends RecyclerView.Adapter<MesgAdapter.MesgViewHolder> {

    private TreeSet<FriendWithMesg> mesgSet;
    private Context context;
    private LayoutInflater inflater;

    public MesgAdapter(Context context, TreeSet<FriendWithMesg> mesgSet) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.mesgSet = mesgSet;
        Log.e("aaa", mesgSet.toString());
    }


    @Override
    public MesgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.message_item_chat, parent, false);
        return new MesgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MesgViewHolder holder, int position) {

        FriendWithMesg mesg = getMessage(position);
        GlideUtils.loadCircleImage(context, mesg.getHeadimage(),
                holder.headImage, R.mipmap.ic_launcher);
        holder.nickName.setText(mesg.getNickname());
        holder.lastMessage.setText(mesg.getLastChatMesg());

    }

    private FriendWithMesg getMessage(int position) {
        Iterator<FriendWithMesg> iterator = mesgSet.iterator();
        for (int i = 0; i <= position; ++i) {
            iterator.next();
        }
        return iterator.next();
    }

    @Override
    public int getItemCount() {
        return mesgSet.size();
    }

    class MesgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.head_image)
        ImageView headImage;
        @BindView(R.id.nick_name)
        TextView nickName;
        @BindView(R.id.last_message)
        TextView lastMessage;

        public MesgViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void onClick(View v) {
            //TODO:页面跳转
            MainActivity activity = (MainActivity) context;
            Bundle bundle = new Bundle();
            FriendWithMesg message = getMessage(getAdapterPosition());
            Friend friend = new Friend(message);
            bundle.putParcelable(ChatActivity.FRIEND, friend);
            activity.openActivity(ChatActivity.class, bundle);
        }

    }
}
