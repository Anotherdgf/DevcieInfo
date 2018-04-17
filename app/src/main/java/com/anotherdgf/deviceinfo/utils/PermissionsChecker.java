package com.anotherdgf.deviceinfo.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by denggaofeng on 2018/4/17.
 */

public class PermissionsChecker {
    private final Context mContext;

    public PermissionsChecker(Context context){
        mContext = context.getApplicationContext();
    }

    public boolean lacksPermissions(String... Permission){
        for (String permission:Permission){
            if(lackPermission(permission)){
                return true;
            }
        }
        return false;
    }

    public boolean lackPermission(String permission){
        return ContextCompat.checkSelfPermission(mContext,permission) ==
                PackageManager.PERMISSION_DENIED;
    }
}
