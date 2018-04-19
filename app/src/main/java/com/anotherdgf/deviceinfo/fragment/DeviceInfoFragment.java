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

    private final static String TAG = "DeviceInfoFragment";

    public static DeviceInfoFragment newInstance() {
        return new DeviceInfoFragment();
    }

    private TextView tv_maunfacturer;
    private TextView tv_brand;
    private TextView tv_model;
    private TextView tv_serialNo;
    private TextView tv_androidId;
    private TextView tv_board;
    private TextView tv_user;
    private TextView tv_host;

    @Override
    protected void initView(View view, Bundle savedInstanceState){
        tv_maunfacturer = view.findViewById(R.id.maunfacturer_value);
        tv_brand = view.findViewById(R.id.brand_value);
        tv_model = view.findViewById(R.id.model_value);
        tv_serialNo = view.findViewById(R.id.serialNo_value);
        tv_androidId = view.findViewById(R.id.androidId_value);
        tv_board = view.findViewById(R.id.board_value);
        tv_user = view.findViewById(R.id.user_value);
        tv_host = view.findViewById(R.id.host_value);

        presentValues();

    }

    private void presentValues(){
        if (null != SystemUtils.getDeviceBrand()){
            tv_maunfacturer.setText(SystemUtils.getDeviceBrand());
        }
        if (null != SystemUtils.getDeviceBrand()){
            tv_brand.setText(SystemUtils.getDeviceBrand());
        }
        if (null != SystemUtils.getSystemModel()){
            tv_model.setText(SystemUtils.getSystemModel());
        }
        if (null != SystemUtils.getSerialNo()){
            tv_serialNo.setText(SystemUtils.getSerialNo());
        }
        if (null != SystemUtils.getBuildHost()){
            tv_host.setText(SystemUtils.getBuildHost());
        }
        if (null != SystemUtils.getBuildId()){
            tv_androidId.setText(SystemUtils.getBuildId());
        }
        if (null != SystemUtils.getBuildBoard()){
            tv_board.setText(SystemUtils.getBuildBoard());
        }
        if (null != SystemUtils.getBuildUser()){
            tv_user.setText(SystemUtils.getBuildUser());
        }
    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_deviceinfo;
    }
}
