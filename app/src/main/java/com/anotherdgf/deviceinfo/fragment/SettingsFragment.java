package com.anotherdgf.deviceinfo.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.service.CurrentActivityService;
import com.anotherdgf.deviceinfo.utils.DialogUtil;
import com.anotherdgf.deviceinfo.utils.PermissionUtils;
import com.anotherdgf.deviceinfo.window.WindowViewContainer;

/**
 * Created by DGF on 2018/4/17.
 */

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{

    private final static String TAG = "SettingsFragment";

    private static final String KEY_CURRENT_ACTIVITY = "current_activity";

    private SharedPreferences prefs;
    private SwitchPreference mSwitchPreference;
    private SharedPreferences.Editor editor;
    private boolean isChecked;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_main_pref);

        //sharedPreference
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
        isChecked = prefs.getBoolean("current_switch", false);

        if (!PermissionUtils.getServiceState(getActivity(), CurrentActivityService.SERVICE_NAME)){
            isChecked = false;
        }

        mSwitchPreference = (SwitchPreference)findPreference(KEY_CURRENT_ACTIVITY);
        mSwitchPreference.setChecked(isChecked);
        mSwitchPreference.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        // TODO Auto-generated method stub
        if (preference.getKey().equals(KEY_CURRENT_ACTIVITY)){
            isChecked = !isChecked;
            if (isChecked){
                //TODO 权限检查，辅助权限开启
                initCurrentActivity();
                // 切换视图显示状态
                WindowViewContainer.getInstance(getActivity()).switchWindowView();
            }else {
                WindowViewContainer.getInstance(getActivity()).switchWindowView();
            }
        }
        return false;
    }

    private void initCurrentActivity(){

        // 检查用户是否已授权开启"辅助功能"
        if (!PermissionUtils.getServiceState(getActivity(), CurrentActivityService.SERVICE_NAME)) {
            DialogUtil.showAccessibilityServiceAlertDialog(getActivity(), getString(R.string.accessibilityService_msg), getString(R.string.cancle), getString(R.string.confirm));
        }
        return;
    }

    @Override
    public void onStop(){
        super.onStop();
        editor.putBoolean("current_switch",isChecked);
        editor.commit();
    }

}
