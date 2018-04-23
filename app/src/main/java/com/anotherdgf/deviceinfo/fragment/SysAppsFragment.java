package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.adapter.ApplicationAdapter;
import com.anotherdgf.deviceinfo.bean.ApplicationBean;
import com.anotherdgf.deviceinfo.utils.ApplicationUtils;

import java.util.ArrayList;

/**
 * Created by denggaofeng on 2018/4/19.
 */

public class SysAppsFragment extends BaseFragment implements OnItemClickListener {

    private final static String TAG = "SysAppsFragment";
    private ListView sysAppsListView;
    private ArrayList<ApplicationBean> mSysApplications = null;

    public static SysAppsFragment newInstance() {
        return new SysAppsFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){
        sysAppsListView = view.findViewById(R.id.sys_apps_listview);

        ApplicationUtils applicationUtils = new ApplicationUtils(getContext());

        mSysApplications = applicationUtils.getAllApps(true);

        ApplicationAdapter mSysAdapter = new ApplicationAdapter(getContext(),mSysApplications);
        sysAppsListView.setAdapter(mSysAdapter);
        sysAppsListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id){

    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_sys_apps;
    }
}
