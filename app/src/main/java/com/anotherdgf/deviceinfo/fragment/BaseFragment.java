package com.anotherdgf.deviceinfo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anotherdgf.deviceinfo.activity.BaseActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by DGF on 2018/4/17.
 */

public abstract class BaseFragment extends Fragment{

    protected BaseActivity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected BaseActivity getHoldingActivity(){
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.mActivity = (BaseActivity)activity;
    }

    //add fragment
    protected void addFragment(BaseFragment fragment){
        if (null != fragment){
            getHoldingActivity().addFragment(fragment);
        }
    }

    //remove fragment
    protected void removeFragment(){
        getHoldingActivity().removeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(getLayoutId(),container,false);
        initView(view,savedInstanceState);
        return view;
    }

    @Override
    public void onPause(){
        super.onPause();
    }
}
