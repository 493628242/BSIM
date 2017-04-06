package com.wangjiyuan.im.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    protected Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


    //传参跳转
    public void openActivity(Class trgetActivity, Bundle bundle) {
        Intent intent = new Intent(this, trgetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    //不传参跳转
    public void openActivity(Class targetActivity) {
        openActivity(targetActivity, null);
    }

    //传参跳转并结束自身
    public void openActivityAndFinish(Class targetActivity, Bundle bundle) {
        openActivity(targetActivity, bundle);
        finish();
    }

    //不传参跳转并结束自身
    public void openActivityAndFinish(Class targetActivity) {
        openActivity(targetActivity, null);
        finish();
    }

    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
