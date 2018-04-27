package com.anotherdgf.deviceinfo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.anotherdgf.deviceinfo.fragment.BaseFragment;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by denggaofeng on 2018/4/18.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private ImmersionBar mImmersionBar;
    //获取布局文件ID
    protected abstract int getContentViewId();

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }

    //添加Fragment
    public void addFragment(BaseFragment fragment){
        if (null != fragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(),fragment,fragment.getClass().getSimpleName())
//                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    public void removeFragment(){
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    //返回事件处理
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }

        return super.onKeyDown(keyCode,event);
    }
}
