package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.view.View;

import com.anotherdgf.deviceinfo.R;

/**
 * Created by denggaofeng on 2018/4/19.
 */

public class SysAppsFragment extends BaseFragment {

    private final static String TAG = "SysAppsFragment";

    public static SysAppsFragment newInstance() {
        return new SysAppsFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){

    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_sys_apps;
    }
}
