package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.anotherdgf.deviceinfo.R;

/**
 * Created by DGF on 2018/4/17.
 */

public class SettingsFragment extends PreferenceFragment{

    private final static String TAG = "SettingsFragment";

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_main_pref);
    }

}
