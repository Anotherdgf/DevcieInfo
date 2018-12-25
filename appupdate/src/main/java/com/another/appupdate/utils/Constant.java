package com.another.appupdate.utils;

public final class Constant {
    /**
     * 网络连接超时时间
     */
    public static final int HTTP_TIME_OUT = 5000;
    /**
     * 动态申请权限请求码
     */
    public static final int PERMISSION_REQUEST_CODE = 1997;
    /**
     * Logcat日志输出Tag
     */
    public static final String TAG = "AppUpdate.";
    /**
     * 渠道通知id
     */
    public static final String DEFAULT_CHANNEL_ID = "appUpdate";
    /**
     * 渠道通知名称
     */
    public static final String DEFAULT_CHANNEL_NAME = "版本更新";
    /**
     * 新版本下载线程名称
     */
    public static final String THREAD_NAME = "app update thread";
    /**
     * 新版本断点下载保存下载进度的key
     */
    public static final String PROGRESS = "app_update_progress";
    /**
     * apk文件后缀
     */
    public static final String APK_SUFFIX = ".apk";

}
