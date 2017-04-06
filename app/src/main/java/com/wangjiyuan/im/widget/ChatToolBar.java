package com.wangjiyuan.im.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.activity.usermesg.UserMesgActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wjy on 2017/2/16.
 * <p>
 * 自定义View用于聊天界面的标题
 */

public class ChatToolBar extends RelativeLayout {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.chat_object)
    TextView chatObject;
    @BindView(R.id.friend_mesg)
    ImageView friendMesg;
    private Intent intent;

    public ChatToolBar(Context context) {
        this(context, null);
    }

    public ChatToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chat_tool_bar, this, true);
        ButterKnife.bind(view);
    }

    @OnClick({R.id.back_btn, R.id.friend_mesg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
                break;
            case R.id.friend_mesg:
                getContext().startActivity(intent);
                break;
        }
    }

    public void setText(String text) {
        chatObject.setText(text);
    }

    public void seeFriendInfo(Activity activity, String phonenumber) {
        intent = new Intent(activity, UserMesgActivity.class);
        intent.putExtra(UserMesgActivity.USER, phonenumber);

    }
}
