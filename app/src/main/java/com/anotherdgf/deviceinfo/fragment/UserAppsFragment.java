package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.view.View;

import com.anotherdgf.deviceinfo.R;

/**
 * Created by denggaofeng on 2018/4/19.
 */

public class UserAppsFragment extends BaseFragment {

    private final static String TAG = "UserAppsFragment";

    public static UserAppsFragment newInstance() {
        return new UserAppsFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){

    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_user_apps;
    }
}
