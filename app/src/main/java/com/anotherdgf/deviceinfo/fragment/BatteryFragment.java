package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.view.View;

import com.anotherdgf.deviceinfo.R;

/**
 * Created by denggaofeng on 2018/5/14.
 */

public class BatteryFragment extends BaseFragment{
    private final static String TAG = "DonateMeFragment";

    public static BatteryFragment newInstance() {
        return new BatteryFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){

    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_battery;
    }
}
