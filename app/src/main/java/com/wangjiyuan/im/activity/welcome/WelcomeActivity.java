package com.wangjiyuan.im.activity.welcome;

import android.os.AsyncTask;
import android.os.Bundle;

import com.wangjiyuan.im.base.BaseActivity;
import com.wangjiyuan.im.base.BaseApplication;
import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.config.SharedConfig;
import com.wangjiyuan.im.activity.login.LoginActivity;
import com.wangjiyuan.im.activity.main.MainActivity;
import com.wangjiyuan.im.utils.HttpUtils;
import com.wangjiyuan.im.utils.SharedPreferenceUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ConnectService().execute();
    }

    class ConnectService extends AsyncTask<Void, Void, Integer> {

        private User user;

        @Override
        protected Integer doInBackground(Void... params) {
            String token = SharedPreferenceUtil.getString(mContext, SharedConfig.TOKEN, null);
            String phonenumber = SharedPreferenceUtil.getString(mContext, SharedConfig.PHONE_NUBER, null);
            if (token != null && phonenumber != null) { //发送token给服务器
                Call<User> verify = HttpUtils.getInstance().getHttpInterfaces().Verify(phonenumber, token);
                try {
                    Response<User> response = verify.execute();
                    user = response.body();
                    if (user.getToken().equals("000000")) {
                        return 0;
                    }
                    SharedPreferenceUtil.putString(mContext, SharedConfig.PHONE_NUBER, this.user.getPhonenumber());
                    SharedPreferenceUtil.putString(mContext, SharedConfig.TOKEN, this.user.getToken());
                    BaseApplication.setUser(user);
                } catch (IOException e) {
                    e.printStackTrace();
                    return 0;
                }
                return 1;
            } else {//本地没有用户已登录，返回0
                return 0;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (integer == 0) { //代表本地没有用户，页面跳转到登录画面
                openActivityAndFinish(LoginActivity.class);
            } else if (integer == 1) { //代表本地有用户，且连接服务器成功
                openActivityAndFinish(MainActivity.class);
            }
        }
    }
}
