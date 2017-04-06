package com.wangjiyuan.im.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.adapter.MainViewPagerAdapter;
import com.wangjiyuan.im.base.BaseActivity;
import com.wangjiyuan.im.base.BaseApplication;
import com.wangjiyuan.im.base.BaseFragment;
import com.wangjiyuan.im.bean.Message;
import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.fragment.friend.FriendFragment;
import com.wangjiyuan.im.fragment.message.MesgFragment;
import com.wangjiyuan.im.activity.usermesg.UserMesgActivity;
import com.wangjiyuan.im.handler.ChatMessageHandler;
import com.wangjiyuan.im.utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ChatMessageHandler.onReceiveMessageListener {


    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.add_friend)
    ImageView addFriend;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.friend_mesg)
    RadioButton friendMesg;
    @BindView(R.id.friend)
    RadioButton friend;

    private ArrayList<BaseFragment> fragments;
    private MainViewPagerAdapter adapter;

    private ImageView nav_headImage;
    private TextView nav_userName;
    FriendFragment friendFragment;
    MesgFragment mesgFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initNav();
        ConnectService();
    }

    private void ConnectService() {
        ChatMessageHandler handler = new ChatMessageHandler(getApplicationContext());
        BaseApplication.APP.setChatMessageHandler(handler);
    }


    private void initView() {
        GlideUtils.loadCircleImage(this, BaseApplication.getUser().getHeadimage(), headImage, R.mipmap.ic_launcher);
        navigationView.setNavigationItemSelectedListener(this);
        fragments = new ArrayList<>();
        friendFragment = new FriendFragment();
        mesgFragment = new MesgFragment();
        fragments.add(mesgFragment);
        fragments.add(friendFragment);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        friendMesg.setChecked(true);
                        break;
                    case 1:
                        friend.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initNav() {
        View headerView = navigationView.getHeaderView(0);
        nav_headImage = (ImageView) headerView.findViewById(R.id.head_image);
        nav_userName = (TextView) headerView.findViewById(R.id.user_name);
        nav_userName.setText(BaseApplication.getUser().getNickname());
        GlideUtils.loadCircleImage(this, BaseApplication.getUser().getHeadimage(), nav_headImage, R.mipmap.ic_launcher);
        nav_headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(UserMesgActivity.USER, BaseApplication.getUser().getPhonenumber());
                openActivity(UserMesgActivity.class, bundle);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.getChatMessageHandler().setMainActivitySoftReference(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.friend_mesg, R.id.friend, R.id.head_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.friend_mesg:
                viewPager.setCurrentItem(0);
                title.setText("消息");
                break;
            case R.id.friend:
                viewPager.setCurrentItem(1);
                title.setText("好友");
                break;
            case R.id.head_image:

                drawer.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void onReceiveMessageListener(ArrayList<Message> Message) {
        mesgFragment.onReceiveMessageListener(Message);
    }
}
