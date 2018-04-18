package com.anotherdgf.deviceinfo.utils;

import android.os.Build;

import java.security.Security;
import java.util.Locale;

/**
 * Created by denggaofeng on 2018/4/18.
 */

public class SystemUtils {

    /**
    * 获取系统当前语言
    * @return 系统当前语言
    * Created at 2018/4/18 16:18 by dgf
    */
    public static String getSystemLanguage(){
        return Locale.getDefault().getLanguage();
    }

    /**
    * 获取当前手机系统版本号
    * @return 系统版本号
    * Created at 2018/4/18 16:20 by dgf
    */
    public static String getSystemVersion(){
        return Build.VERSION.RELEASE;
    }

    /**
    *获取手机型号
    * @return 手机型号
    * Created at 2018/4/18 16:21 by dgf
    */
    public static String getSystemModel(){
        return Build.MODEL;
    }

    /**
    * 获取手机厂商
    * @return 手机厂商
    * Created at 2018/4/18 16:24 by dgf
    */
    public static String getDeviceBrand(){
        return Build.BRAND;
    }

    /**
    * 获取IMEI信息
    * @return IMEI信息
    * Created at 2018/4/18 16:27 by dgf
    */
//    public static String getIMEI(Context context){
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
//        if(null != tm){
//            return tm.getDeviceId();
//        }
//        return null;
//    }
}
