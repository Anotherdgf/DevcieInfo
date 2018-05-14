package com.anotherdgf.deviceinfo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.anotherdgf.deviceinfo.R;

/**
 * Created by denggaofeng on 2018/5/14.
 */

public class SplashActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            SharedPreferences sp = getSharedPreferences("wear", Activity.MODE_PRIVATE);
            if(sp.getBoolean("isLogin",false)){
                goNext(MainActivity.class);
            }else {
                goNext(MainActivity.class);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(r,2000);
    }

    private void goNext(Class c){
        Intent i = new Intent();
        i.setClass(this,c);
        startActivity(i);
        finish();
    }
}
