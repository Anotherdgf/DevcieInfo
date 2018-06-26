package com.anotherdgf.deviceinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.fragment.RecyclerDemoFragment;
import com.anotherdgf.deviceinfo.fragment.SettingsFragment;
import com.anotherdgf.deviceinfo.service.NetworkStateService;
import com.anotherdgf.deviceinfo.utils.EventUtil;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by denggaofeng on 2018/6/25.
 */

public class RecyclerViewActivity extends BaseActivity {

    private static final String TAG = "RecyclerViewActivity";

    private Toolbar sToolbar;
    protected ImmersionBar mImmersionBar;

    private Intent netintent;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        EventBus.getDefault().register(this);

        initView();
        setStatusBar();

        netintent = new Intent(this, NetworkStateService.class);
        netintent.setAction("com.smart.services.NetworkStateService");
        startService(netintent);

    }

    private void initView(){
        sToolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        sToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addFragment(RecyclerDemoFragment.newInstance());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetWorkListener(EventUtil.NetWorkStatEvent event){
        Log.d(TAG,"EventBus message arrivied");
        if (event != null && event.getState() == 0) {
            Toast.makeText(this,getString(R.string.network_error),Toast.LENGTH_SHORT).show();
        }
    }

    protected void setStatusBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.colorPrimaryDark)
                .fitsSystemWindows(true)
                .init();
    }


    @Override
    protected int getContentViewId(){
        return R.layout.activity_recyclerviews;
    }

    @Override
    protected int getFragmentContentId(){
        return R.id.fragment_container;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        stopService(netintent);
        EventBus.getDefault().unregister(this);
    }
}
