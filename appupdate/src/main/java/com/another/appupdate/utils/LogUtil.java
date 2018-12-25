package com.another.appupdate.utils;

import android.util.Log;

import com.another.appupdate.manager.DownloadManager;

public final class LogUtil {

    /**
     * 日志开关
     */
    private static boolean b;

    static {
        b = DownloadManager.getInstance().getConfiguration().isEnableLog();
    }

    /**
     * 输出Error信息
     *
     * @param tag tag
     * @param msg String
     */
    public static void e(String tag, String msg) {
        if (b) {
            Log.e(tag, msg);
        }
    }

    /**
     * 输出Error信息
     *
     * @param tag tag
     * @param msg int
     */
    public static void e(String tag, int msg) {
        if (b) {
            Log.e(tag, String.valueOf(msg));
        }
    }

    /**
     * 输出Error信息
     *
     * @param tag tag
     * @param msg float
     */
    public static void e(String tag, float msg) {
        if (b) {
            Log.e(tag, String.valueOf(msg));
        }
    }

    /**
     * 输出Error信息
     *
     * @param tag tag
     * @param msg Long
     */
    public static void e(String tag, Long msg) {
        if (b) {
            Log.e(tag, String.valueOf(msg));
        }
    }

    /**
     * 输出Error信息
     *
     * @param tag tag
     * @param msg double
     */
    public static void e(String tag, double msg) {
        if (b) {
            Log.e(tag, String.valueOf(msg));
        }
    }

    /**
     * 输出Error信息
     *
     * @param tag tag
     * @param msg boolean
     */
    public static void e(String tag, boolean msg) {
        if (b) {
            Log.e(tag, String.valueOf(msg));
        }
    }

    /**
     * 输出Debug信息
     *
     * @param tag tag
     * @param msg String
     */
    public static void d(String tag, String msg) {
        if (b) {
            Log.d(tag, msg);
        }
    }

    /**
     * 输出Info信息
     *
     * @param tag tag
     * @param msg 字符串
     */
    public static void i(String tag, String msg) {
        if (b) {
            Log.i(tag, msg);
        }
    }
}
