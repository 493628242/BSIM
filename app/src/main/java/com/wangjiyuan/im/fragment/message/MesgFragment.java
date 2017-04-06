package com.wangjiyuan.im.fragment.message;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.adapter.MesgAdapter;
import com.wangjiyuan.im.base.BaseFragment;
import com.wangjiyuan.im.bean.FriendWithMesg;
import com.wangjiyuan.im.bean.Message;
import com.wangjiyuan.im.config.UrlConfig;
import com.wangjiyuan.im.handler.ChatMessageHandler;

import java.util.ArrayList;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class MesgFragment extends BaseFragment implements ChatMessageHandler.onReceiveMessageListener {


    @BindView(R.id.message_list)
    RecyclerView messageList;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private MesgAdapter adapter;
    private TreeSet<FriendWithMesg> mesgList;

    public MesgFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mesg, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mesgList = new TreeSet<>();
        adapter = new MesgAdapter(mContext, mesgList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        messageList.setAdapter(adapter);
        messageList.setLayoutManager(manager);
        messageList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onReceiveMessageListener(ArrayList<Message> messages) {
        for (FriendWithMesg fwm :
                mesgList) {
            for (Message m : messages) {
                if (fwm.getPhonenumber().equals(m.getForm())) {
                    mesgList.remove(fwm);
                    FriendWithMesg f = new FriendWithMesg();
                    f.setHeadimage(UrlConfig.BASE_URL + UrlConfig.HEAD_IMAGE + m.getForm() + ".jpg");
                    f.setNickname(m.getFormnickname());
                    f.setLastChatMesg(m.getType() == 1 ? m.getContent() : "[图片]");
                    f.setTime(m.getTime());
                    mesgList.add(f);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}
