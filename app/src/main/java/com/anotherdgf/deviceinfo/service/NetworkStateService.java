package com.anotherdgf.deviceinfo.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

import com.anotherdgf.deviceinfo.utils.EventUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by denggaofeng on 2018/6/26.
 */

public class NetworkStateService extends Service {

    private static final String TAG = "NetworkStateService";

    // Class that answers queries about the state of network connectivity.
    private ConnectivityManager connectivityManager;

    // Describes the status of a network interface.
    private NetworkInfo info;

    /**
     * 当前处于的网络
     * 0 ：null
     * 1 ：2G/3G/4G
     * 2 ：wifi
     */
    public static int networkStatus;

    /**
     * 广播实例
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // The action of this intent or null if none is specified.
            String action = intent.getAction(); //当前接受到的广播的标识(行动/意图)

            // 当当前接受到的广播的标识(意图)为网络状态的标识时做相应判断
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                // 获取网络连接管理器
                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                // 获取当前网络状态信息
                info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isAvailable()) {

                    //当NetworkInfo不为空且是可用的情况下，获取当前网络的Type状态
                    //根据NetworkInfo.getTypeName()判断当前网络
                    String name = info.getTypeName();

                    //更改NetworkStateService的静态变量，之后只要在Activity中进行判断就好了
                    if (name.equals("WIFI")) {
                        networkStatus = 2;
                    } else {
                        networkStatus = 1;
                    }
                } else {
                    // NetworkInfo为空或者是不可用的情况下
                    networkStatus = 0;
                }

                //监听到网络状态变化，及时通过eventbus消息总线通知主界面进行显示提示
                EventBus.getDefault().post(new EventUtil.NetWorkStatEvent(networkStatus));

            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //注册网络状态的广播，绑定到mReceiver
        Log.d(TAG,"网络服务开启");
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销接收
        unregisterReceiver(mReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        // 获取网络连接管理器
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // 获取当前网络状态信息
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }

        return false;
    }
}
