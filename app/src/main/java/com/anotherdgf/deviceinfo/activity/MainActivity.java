package com.anotherdgf.deviceinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.fragment.AboutMeFragment;
import com.anotherdgf.deviceinfo.fragment.BaseFragment;
import com.anotherdgf.deviceinfo.fragment.BatteryFragment;
import com.anotherdgf.deviceinfo.fragment.DeviceInfoFragment;
import com.anotherdgf.deviceinfo.fragment.DialogsFragment;
import com.anotherdgf.deviceinfo.fragment.DonateMeFragment;
import com.anotherdgf.deviceinfo.fragment.SysAppsFragment;
import com.anotherdgf.deviceinfo.fragment.UserAppsFragment;
import com.anotherdgf.deviceinfo.service.CurrentActivityService;
import com.anotherdgf.deviceinfo.service.MovementStateService;
import com.anotherdgf.deviceinfo.utils.DialogUtil;
import com.anotherdgf.deviceinfo.utils.PermissionUtils;
import com.anotherdgf.deviceinfo.utils.SystemUtils;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private static boolean isExit = false; //标志是否退出

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private TextView tv_nav_header;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        initView();
        initFragmentView();

        //悬浮窗权限检查，没有给出弹窗提醒
        if (!PermissionUtils.hasOverlayPermission(this)) {
            DialogUtil.showOverlayAlertDialog(this);
        }

        //开启服务
        intent = new Intent(this, MovementStateService.class);
        startService(intent);
    }

    private void initView(){
        toolbar =findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.getHeaderView(0);
        tv_nav_header = headerView.findViewById(R.id.text_nav_header);
        if (null != SystemUtils.getSystemModel()){
            tv_nav_header.setText(SystemUtils.getSystemModel());
        }
    }

    private void initFragmentView(){
        toolbar.setTitle(R.string.nav_devices);
        addFragment(DeviceInfoFragment.newInstance());
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
           // case R.id.nav_header:
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_settings:
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_deviceinfo:
                toolbar.setTitle(R.string.nav_devices);
                addFragment(DeviceInfoFragment.newInstance());
                break;
            case R.id.nav_user_apps:
                toolbar.setTitle(R.string.nav_user_apps);
                addFragment(UserAppsFragment.newInstance());
                break;
            case R.id.nav_sys_apps:
                toolbar.setTitle(R.string.nav_sys_apps);
                addFragment(SysAppsFragment.newInstance());
                break;
            case R.id.nav_battery:
                toolbar.setTitle(R.string.nav_battery);
                addFragment(BatteryFragment.newInstance());
                break;
            case R.id.nav_about:
                toolbar.setTitle(R.string.nav_about);
                addFragment(AboutMeFragment.newInstance());
                break;
            case R.id.nav_donate:
                toolbar.setTitle(R.string.nav_donate);
                addFragment(DonateMeFragment.newInstance());
                break;
            case R.id.demo_dialogs:
                toolbar.setTitle(R.string.nav_demo_dialogs);
                addFragment(DialogsFragment.newInstance());
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            exit();
//            super.onBackPressed();
        }
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);  // 利用handler延迟发送更改状态信息
        } else {
            this.finish();
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        stopService(intent);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
