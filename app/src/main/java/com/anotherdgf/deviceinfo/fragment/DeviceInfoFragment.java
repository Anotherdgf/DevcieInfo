package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.utils.SystemUtils;

/**
 * Created by denggaofeng on 2018/4/18.
 */

public class DeviceInfoFragment extends BaseFragment {

    public static DeviceInfoFragment newInstance() {
        return new DeviceInfoFragment();
    }

    private TextView tv_brand;
    private TextView tv_maunfacturer;
    private TextView tv_serialNo;

    @Override
    protected void initView(View view, Bundle savedInstanceState){
        tv_brand = (TextView)view.findViewById(R.id.brand_value);
        tv_brand.setText(SystemUtils.getDeviceBrand());
        tv_maunfacturer = view.findViewById(R.id.maunfacturer_value);
        tv_maunfacturer.setText(SystemUtils.getDeviceBrand());
    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_deviceinfo;
    }
}
