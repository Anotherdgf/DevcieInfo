package com.anotherdgf.deviceinfo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.anotherdgf.deviceinfo.service.CurrentActivityService;
import com.anotherdgf.deviceinfo.window.WindowViewContainer;

/**
 * Created by denggaofeng on 2018/5/10.
 */

public class CurrentActivityServiceReceiver extends BroadcastReceiver {

    /**
     * 显示隐藏悬浮窗广播action
     */
    public static final String SWITCH_ACTION = "com.anotherdgf.intent.action.SWITCH";

    /**
     * 关闭悬浮窗action名
     */
    public static final String CLOSE_ACTION = "com.anotherdgf.intent.action.CLOSE";

    @Override
    public void onReceive(Context context, Intent intent) {

        //只处理"辅助服务"的Intent消息
        if (!(context instanceof CurrentActivityService)){
            return;
        }
        if (null == intent.getAction()){
            return;
        }
        switch (intent.getAction()){
            case SWITCH_ACTION:
                WindowViewContainer.getInstance(context).switchWindowView();
                break;
            case CLOSE_ACTION:
                CurrentActivityService service = (CurrentActivityService)context;
                //Android7.0以上可直接调用API方法关闭辅助服务
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    service.disableSelf();
                }
                break;
            default:
                break;
        }
    }
}
