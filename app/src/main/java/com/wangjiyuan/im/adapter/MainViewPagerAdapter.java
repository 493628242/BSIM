package com.wangjiyuan.im.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wangjiyuan.im.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by wjy on 2017/3/1.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> fragments;

    public MainViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
