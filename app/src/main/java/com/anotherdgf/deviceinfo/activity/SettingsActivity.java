package com.anotherdgf.deviceinfo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.fragment.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar sToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();

    }

    private void initView(){
        sToolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new SettingsFragment())
                .commit();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
