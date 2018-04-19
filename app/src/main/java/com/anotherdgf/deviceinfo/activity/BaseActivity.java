package com.anotherdgf.deviceinfo.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.anotherdgf.deviceinfo.fragment.BaseFragment;

/**
 * Created by denggaofeng on 2018/4/18.
 */

public abstract class BaseActivity extends AppCompatActivity{

    //获取布局文件ID
    protected abstract int getContentViewId();

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();

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
