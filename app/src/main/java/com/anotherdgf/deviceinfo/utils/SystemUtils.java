package com.anotherdgf.deviceinfo.utils;

import android.os.Build;

/**
 * Created by denggaofeng on 2018/4/18.
 */

public class SystemUtils {

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
    * 获取设备序列号
    * @return 设备序列号
    * Created at 2018/4/19 13:58 by dgf
    */
    public static String getSerialNo(){
        return Build.SERIAL;
    }

    /**
    * 
    * @return 
    * Created at 2018/4/19 14:02 by dgf
    */
    public static String getBuildHost(){
        return Build.HOST;
    }

    public static String getBuildUser(){
        return Build.USER;
    }

    public static String getBuildBoard(){
        return Build.BOARD;
    }

    public static String getBuildId(){
        return Build.ID;
    }

    public static String getBootloader(){
        return Build.BOOTLOADER;
    }

}
