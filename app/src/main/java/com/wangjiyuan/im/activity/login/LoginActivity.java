package com.wangjiyuan.im.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.wangjiyuan.im.R;
import com.wangjiyuan.im.base.BaseActivity;
import com.wangjiyuan.im.activity.main.MainActivity;
import com.wangjiyuan.im.base.BaseApplication;
import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.config.ConfigString;
import com.wangjiyuan.im.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView {

    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private boolean wait = true;
    private int n = 0;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        //发送用户名和密码并等待服务器返回信息,按钮设为不可用
        if (TextUtils.isEmpty(phoneNumber.getText()) || TextUtils.isEmpty(password.getText())) {
            showToast("用户名密码不能为空");
            return;
        }
        presenter.getUserMessage(phoneNumber.getText().toString(), password.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (wait) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (n % 2 == 0) {
                                btnLogin.setText("登录中。");
                            } else {
                                btnLogin.setText("登录中。。");
                            }
                            ++n;

                        }
                    });
                }
            }
        }).start();
        btnLogin.setClickable(false);
    }


    @Override
    public void LoginSuccess(User user) {
        //页面跳转
        BaseApplication.setUser(user);
        SharedPreferenceUtil.putString(this, ConfigString.PHONE_NUBER, user.getPhonenumber());
        SharedPreferenceUtil.putString(this, ConfigString.TOKEN, user.getToken());
        wait = false;
        openActivityAndFinish(MainActivity.class);
    }

    @Override
    public void LoginFailure() {
        //弹出Toast
        wait = false;
        btnLogin.setText("登录");
        btnLogin.setClickable(true);
        showToast("登录失败");

    }
}
