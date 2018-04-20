package com.anotherdgf.deviceinfo.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by denggaofeng on 2018/4/20.
 */

public class ApplicationBean {

    private String appName;
    private String packageName;
    private Drawable apkIcon;

    public ApplicationBean(){
    }

    public ApplicationBean(String appName,String packageName,Drawable apkIcon){
        this.appName = appName;
        this.packageName = packageName;
        this.apkIcon = apkIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getApkIcon() {
        return apkIcon;
    }

    public void setApkIcon(Drawable apkIcon) {
        this.apkIcon = apkIcon;
    }

    @Override
    public String toString() {
        return "ApplicationBean{" +
                "appName='" + appName + '\'' +
                '}';
    }
}
