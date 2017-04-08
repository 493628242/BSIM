package com.wangjiyuan.im.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.adapter.ChatAdapter;
import com.wangjiyuan.im.base.BaseActivity;
import com.wangjiyuan.im.base.BaseApplication;
import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.bean.Message;
import com.wangjiyuan.im.handler.ChatMessageHandler;
import com.wangjiyuan.im.widget.ChatToolBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ChatMessageHandler.onReceiveMessageListener {

    public static final String FRIEND = "friend";

    @BindView(R.id.chat_tool_bar)
    ChatToolBar chatToolBar;
    @BindView(R.id.chat_content)
    RecyclerView chatContent;
    @BindView(R.id.add_image)
    ImageView addImage;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.send_message)
    TextView sendMessage;
    private ArrayList<Message> messages = null;
    private ChatAdapter adapter;
    private Friend friend;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (messages == null) {
            messages = intent.getParcelableExtra(ChatMessageHandler.MESSAGES);
        } else {
            messages.addAll((ArrayList<Message>) intent.getParcelableExtra(ChatMessageHandler.MESSAGES));

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    //获取 数据
    private void initData() {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        Intent intent = getIntent();
        friend = intent.getParcelableExtra(FRIEND);
        chatToolBar.setText(friend.getNickname());
        chatToolBar.seeFriendInfo(this, friend.getPhonenumber());
    }

    //构建适配器对象
    private void initView() {
        LinearLayoutManager mamager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        chatContent.setLayoutManager(mamager);
        adapter = new ChatAdapter(this, messages, friend);
        chatContent.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.getChatMessageHandler().setChatActivity(this);
    }

    @OnClick({R.id.add_image, R.id.send_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_image:  //图片发送

                break;
            case R.id.send_message:  //文本发送
                Message message = new Message();
                message.setContent(editText.getText().toString());
                message.setForm(BaseApplication.getUser().changToFriend());
                message.setTo(friend);
                message.setType(Message.TEXT);
                message.setTime(System.currentTimeMillis());
                messages.add(message);
                showLastItem();
                editText.setText("");
                break;
        }
    }

    private void showLastItem() {
        adapter.notifyDataSetChanged();
        chatContent.scrollToPosition(messages.size() - 1);
    }


    public Friend getFriend() {
        return friend;
    }

    @Override
    public void onReceiveMessage(ArrayList<Message> messages) {
        for (Message message :
                messages) {
            if (message.getForm().getPhonenumber().equals(friend.getPhonenumber())) {
                this.messages.add(message);
            }
        }
    }
}
