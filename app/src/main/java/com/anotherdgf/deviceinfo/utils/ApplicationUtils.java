package com.anotherdgf.deviceinfo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.anotherdgf.deviceinfo.bean.ApplicationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denggaofeng on 2018/4/20.
 */

public class ApplicationUtils {

    private Context mContext;
    private static final String TAG = "ApplicationUtils";

    public ApplicationUtils(){}

    public ApplicationUtils(Context mContext){
        this.mContext = mContext;
    }

    public ArrayList<ApplicationBean> getAllApps(boolean isSysApps){

        ArrayList<ApplicationBean> apps = new ArrayList<>();
        PackageInfo mPackageInfo = null;

        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setAction(Intent.ACTION_MAIN);

        PackageManager pManager = mContext.getPackageManager();
        List<ResolveInfo> paklist = pManager.queryIntentActivities(intent,0);

        for (int i = 0;i < paklist.size();i++) {
            ResolveInfo pakinfo = paklist.get(i);
            ApplicationBean apkBeanInfo = new ApplicationBean();
            String pakName = pakinfo.activityInfo.packageName;
            try{
                mPackageInfo = pManager.getPackageInfo(pakName,0);
            }catch (Exception e){
                e.printStackTrace();
            }

            if (isSysApps) {
                if ((mPackageInfo.applicationInfo.flags & mPackageInfo.applicationInfo.FLAG_SYSTEM) > 0) {
                    apkBeanInfo.setApkIcon(pManager.getApplicationIcon(mPackageInfo.applicationInfo));
                    apkBeanInfo.setAppName(pManager.getApplicationLabel(mPackageInfo.applicationInfo).toString());
                    apkBeanInfo.setPackageName(mPackageInfo.applicationInfo.packageName);
                    apps.add(apkBeanInfo);
                    Log.d(TAG,"sys:"+apkBeanInfo.getAppName()+":"+apkBeanInfo.getApkIcon()+":"+apkBeanInfo.getPackageName());
                }
            } else if ((mPackageInfo.applicationInfo.flags & mPackageInfo.applicationInfo.FLAG_SYSTEM) <= 0) {
                apkBeanInfo.setApkIcon(pManager.getApplicationIcon(mPackageInfo.applicationInfo));
                apkBeanInfo.setAppName(pManager.getApplicationLabel(mPackageInfo.applicationInfo).toString());
                apkBeanInfo.setPackageName(mPackageInfo.applicationInfo.packageName);
                apps.add(apkBeanInfo);
                Log.d(TAG,"user:"+apkBeanInfo.getAppName()+":"+apkBeanInfo.getApkIcon()+":"+apkBeanInfo.getPackageName());
            }
        }

        return apps;
    }
}
