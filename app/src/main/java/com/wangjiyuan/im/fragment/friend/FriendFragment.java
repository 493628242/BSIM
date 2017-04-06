package com.wangjiyuan.im.fragment.friend;


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
import com.wangjiyuan.im.adapter.FriendAdapter;
import com.wangjiyuan.im.adapter.MesgAdapter;
import com.wangjiyuan.im.base.BaseFragment;
import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.bean.Group;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class FriendFragment extends BaseFragment {


    @BindView(R.id.friend_list)
    RecyclerView friendList;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private FriendAdapter adapter;

    private ArrayList<Group> groupList;

    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        groupList = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            Group group = new Group();
            group.setTitle(i + "");
            groupList.add(group);
            ArrayList<Friend> ff = new ArrayList<>();
            for (int j = 0; j < 3; ++j) {
                Friend f = new Friend();
                f.setNickname(i + "" + j);
                f.setSex(1);
                f.setPhonenumber("123123123");
                f.setHeadimage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2705782015,1921466091&fm=11&gp=0.jpg");
                ff.add(f);
            }
            group.setChilds(ff);
        }
        adapter = new FriendAdapter(mContext, groupList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        friendList.setAdapter(adapter);
        friendList.setLayoutManager(manager);
        friendList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }
}
