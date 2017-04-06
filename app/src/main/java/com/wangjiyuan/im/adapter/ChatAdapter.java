package com.wangjiyuan.im.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.base.BaseApplication;
import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.bean.Message;
import com.wangjiyuan.im.utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wjy on 2017/2/23.
 */

public class ChatAdapter extends RecyclerView.Adapter {


    private ArrayList<Message> messages;
    private Context context;
    private LayoutInflater inflater;

    private Friend friend;

    public ChatAdapter(Context context, ArrayList<Message> messages, Friend friend) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.messages = messages;
        this.friend = friend;
    }

    @Override
    public int getItemViewType(int position) {
        /**信息的来源与本机来源相同使这使用chat_item_image_right或者chat_item_text_right
         * 否则使用chat_item_image_left或者chat_item_text_left
         */
        Message message = messages.get(position);
        if (message.getForm().equals(BaseApplication.getUser().getPhonenumber())) {
            if (message.getType() == 1) { //文本
                return R.layout.chat_item_text_right;
            } else {    //图片
                return R.layout.chat_item_image_right;
            }
        } else {
            if (message.getType() == 1) { //文本
                return R.layout.chat_item_text_left;
            } else {    //图片
                return R.layout.chat_item_image_left;
            }
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent, false);
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case R.layout.chat_item_text_left:
            case R.layout.chat_item_text_right:
                viewHolder = new MesgTextViewHolder(view);
                break;
            case R.layout.chat_item_image_left:
            case R.layout.chat_item_image_right:
                viewHolder = new MesgImageViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (holder instanceof MesgTextViewHolder) {
            MesgTextViewHolder viewHolder = (MesgTextViewHolder) holder;
            viewHolder.chatText.setText(message.getContent());
            GlideUtils.loadCircleImage(context, friend.getHeadimage(),
                    viewHolder.friendHeadImage, R.mipmap.ic_launcher);
        } else {
            MesgImageViewHolder viewHolder = (MesgImageViewHolder) holder;
            GlideUtils.loadCircleImage(context, friend.getHeadimage(),
                    viewHolder.friendHeadImage, R.mipmap.ic_launcher);
            GlideUtils.loadDefaultImage(context, message.getContent(),
                    viewHolder.chatImage, R.drawable.ic_default_image);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    static class MesgTextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chat_text)
        TextView chatText;
        @BindView(R.id.friend_head_image)
        ImageView friendHeadImage;

        public MesgTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MesgImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chat_image)
        ImageView chatImage;
        @BindView(R.id.friend_head_image)
        ImageView friendHeadImage;

        public MesgImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
