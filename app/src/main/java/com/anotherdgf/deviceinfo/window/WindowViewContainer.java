package com.anotherdgf.deviceinfo.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.anotherdgf.deviceinfo.R;

/**
 * Created by denggaofeng on 2018/5/10.
 */

public class WindowViewContainer {

    private static WindowViewContainer sWindowViewContainer;

    private Context mContext;
    private TextView mTextView;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;
    private boolean isAdded;
    private boolean isShow;

    private WindowViewContainer(Context context){
        mContext = context;
        initView(context);
    }

    public static synchronized WindowViewContainer getInstance(Context context){
        if (null == sWindowViewContainer){
            sWindowViewContainer = new WindowViewContainer(context);
        }
        return sWindowViewContainer;
    }

    private void initView(Context context){
        mTextView = (TextView) LayoutInflater.from(context).inflate(R.layout.current_layer_window, null);
    }

    public void addWindowView(){
        if (isAdded){
            return;
        }
        addView();
    }

    //添加窗口视图
    private void addView(){
        mParams = new WindowManager.LayoutParams();
        mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        //设置类型
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            //android O以下使用
            mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        // 设置标签（FLAG_NOT_FOCUSABLE表示窗口不会获取焦点；FLAG_NOT_TOUCHABLE表示窗口不会接收Touch事件，即将Touch事件向下层分发）
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        // 设置位图模式 （PixelFormat.RGBA_8888可以使背景透明。不设置默认PixelFormat.OPAQUE，即不透明）
        mParams.format = PixelFormat.RGBA_8888;
        // 设置分布位置（距左对齐 + 距顶对齐）
        mParams.gravity = Gravity.LEFT | Gravity.TOP;
        // 设置布局宽/高为自适应
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 添加TextView
        mWindowManager.addView(mTextView, mParams);
        // 记录视图已被添加、显示
        isAdded = true;
        isShow = true;
    }

    //更新窗口视图
    public void updateWindowView(String text){
        if (isShow){
            mTextView.setText(text);
            // 防止某些低版本的手机（或模拟器）按Back键应用退出时，Window窗口被移除无法恢复
            try {
                addView();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //移除窗口视图
    private void removeWindowView(){
        if (isAdded){
            return;
        }
        mWindowManager.removeView(mTextView);
        isAdded = false;
        isShow = false;
    }

     //开关窗口视图
    public void switchWindowView(){
        if (isAdded){
            isShow = !isShow;
            mTextView.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    //获取窗口视图的显示状态
    public boolean getWindowViewShowState(){
        return isAdded && isShow;
    }

    //销毁视图
    public void destoryView(){
        removeWindowView();
        sWindowViewContainer = null;
    }
}
