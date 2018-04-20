package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.util.Log;
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

public class UserAppsFragment extends BaseFragment implements OnItemClickListener {

    private final static String TAG = "UserAppsFragment";

    private ListView userAppsListView;
    private ArrayList<ApplicationBean> mUserApplications = null;

    public static UserAppsFragment newInstance() {
        return new UserAppsFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){
        userAppsListView = view.findViewById(R.id.user_apps_listview);

        ApplicationUtils applicationUtils = new ApplicationUtils(getContext());

        mUserApplications = applicationUtils.getAllApps(false);

        ApplicationAdapter mAdapter = new ApplicationAdapter(getContext(),mUserApplications);
        userAppsListView.setAdapter(mAdapter);
        userAppsListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id){

    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_user_apps;
    }
}
