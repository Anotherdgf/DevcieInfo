package com.anotherdgf.deviceinfo.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.utils.EventUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by denggaofeng on 2018/6/25.
 */

public class RecyclerDemoFragment extends BaseFragment{
    private final static String TAG = "RecyclerDemoFragment";

    public static RecyclerDemoFragment newInstance() {
        return new RecyclerDemoFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){

    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_recycler;
    }

}
