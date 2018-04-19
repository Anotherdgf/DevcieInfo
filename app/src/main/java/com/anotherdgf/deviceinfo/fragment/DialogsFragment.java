package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.view.View;

import com.anotherdgf.deviceinfo.R;

/**
 * Created by denggaofeng on 2018/4/19.
 */

public class DialogsFragment extends BaseFragment {
    private final static String TAG = "DialogsFragment";

    public static DialogsFragment newInstance() {
        return new DialogsFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){

    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment__widget_dialogs;
    }
}
