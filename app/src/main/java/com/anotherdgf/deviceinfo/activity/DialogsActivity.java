package com.anotherdgf.deviceinfo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.fragment.SettingsFragment;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by denggaofeng on 2018/6/25.
 */

public class DialogsActivity extends BaseActivity {

    private Toolbar sToolbar;
    protected ImmersionBar mImmersionBar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        initView();
        setStatusBar();
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

//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container,new SettingsFragment())
//                .commit();

    }

    protected void setStatusBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.colorPrimaryDark)
                .fitsSystemWindows(true)
                .init();
    }

    @Override
    protected int getContentViewId(){
        return R.layout.activity_dialogs;
    }

    @Override
    protected int getFragmentContentId(){
        return R.id.fragment_container;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
