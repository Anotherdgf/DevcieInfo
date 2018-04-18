package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.anotherdgf.deviceinfo.R;

/**
 * Created by DGF on 2018/4/17.
 */

public class SettingsFragment extends BaseFragment{

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){
    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_settings;
    }
}
