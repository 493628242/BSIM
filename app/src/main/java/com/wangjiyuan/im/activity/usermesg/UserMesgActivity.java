package com.wangjiyuan.im.activity.usermesg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.base.BaseActivity;
import com.wangjiyuan.im.bean.Friend;
import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.activity.chat.ChatActivity;
import com.wangjiyuan.im.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class UserMesgActivity extends BaseActivity implements IUserMesgContract.IUserMesgView {
    public static final String PHONE_NUMBER = "phonenumber";
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.nick_name)
    TextView nickName;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.send_message)
    Button sendMessage;
    @BindView(R.id.remove_friend)
    Button removeFriend;
    private Friend friend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mesg);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        bundle.getString(PHONE_NUMBER);
    }


    @Override
    public void LoginSuccess(Friend friend) {
        GlideUtils.loadCircleImage(this, friend.getHeadimage(), headImage, R.mipmap.ic_launcher);
        sex.setText(friend.getSex() == 1 ? "男" : "女");
        nickName.setText(friend.getNickname());
        phoneNumber.setText(friend.getPhonenumber());
        showToast("获取信息成功");
    }

    @Override
    public void LoginFailure() {
        showToast("获取信息失败");
    }

    @OnClick({R.id.back_btn, R.id.send_message, R.id.remove_friend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.send_message:
                Bundle bundle = new Bundle();
                bundle.putParcelable(ChatActivity.FRIEND, friend);
                openActivityAndFinish(ChatActivity.class, bundle);
                break;
            case R.id.remove_friend:
                break;
        }
    }
}
