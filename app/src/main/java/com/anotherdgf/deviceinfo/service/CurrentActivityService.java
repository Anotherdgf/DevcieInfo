package com.anotherdgf.deviceinfo.service;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;

import com.anotherdgf.deviceinfo.receiver.CurrentActivityServiceReceiver;
import com.anotherdgf.deviceinfo.utils.NotificationUtil;
import com.anotherdgf.deviceinfo.window.WindowViewContainer;

/**
 * Created by denggaofeng on 2018/5/10.
 */

public class CurrentActivityService extends AccessibilityService{

    public static final String SERVICE_NAME = "com.anotherdgf.deviceinfo/com.anotherdgf.deviceinfo.service.CurrentActivityService";

    /**
     * 通知栏ID
     */
    public static final int NOTIFICATION_ID = 0x1000;

    /**
     * 窗口视图容器
     */
    private WindowViewContainer mWindowViewContainer;

    /**
     * 广播接收器
     */
    private CurrentActivityServiceReceiver mReceiver;

    /**
     * 通知栏管理器
     */
    private NotificationManager mNotificationManager;

    /**
     * 服务连接完成
     */
    @Override
    protected void onServiceConnected() {
        // 添加窗口
        mWindowViewContainer = WindowViewContainer.getInstance(this);
        mWindowViewContainer.addWindowView();
        //注册广播
        mReceiver = new CurrentActivityServiceReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CurrentActivityServiceReceiver.SWITCH_ACTION);
        intentFilter.addAction(CurrentActivityServiceReceiver.CLOSE_ACTION);
        registerReceiver(mReceiver, intentFilter);
    }

    /**
     * 接收辅助服务事件
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (null == event){
            return;
        }
        switch (event.getEventType()){
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                if (event.getPackageName() != null && event.getClassName() != null){
                    mWindowViewContainer.updateWindowView(event.getPackageName() + "\n" +event.getClassName());
                }
                break;
            default:
                break;
        }
    }

    /**
     * 服务中断
     */
    @Override
    public void onInterrupt(){

    }

    /**
     * 服务退出
     */
    @Override
    public void onDestroy(){
        // 移除窗口视图，销毁视图容器
        if (mWindowViewContainer != null) {
            mWindowViewContainer.destoryView();
            mWindowViewContainer = null;
        }
        // 解注册广播接收器
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
        // 停止前台服务
        stopForeground(true);
        super.onDestroy();
    }
}
