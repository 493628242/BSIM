package com.wangjiyuan.im.base;


import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public BaseFragment() {
        // Required empty public constructor
    }


}
