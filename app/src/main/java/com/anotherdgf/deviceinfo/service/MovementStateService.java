package com.anotherdgf.deviceinfo.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static com.anotherdgf.deviceinfo.constant.LOG_ENABLE;

/**
 * Created by denggaofeng on 2018/5/14.
 */

public class MovementStateService extends Service implements SensorEventListener {

    private final static String TAG = "MovementStateService";

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private int posX = 0;
    private int posY = 0;
    private int posZ = 9;

    private Calendar mCalendar;
    private long lastTimeStamp = 0;

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (mSensorManager == null) {
            if (LOG_ENABLE)Log.d(TAG, "device does not support SensorManager");
        } else {
            //  G-Sensor
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null)
            return;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            int x = (int) event.values[0];
            int y = (int) event.values[1];
            int z = (int) event.values[2];

            mCalendar = Calendar.getInstance();
            long stamp = mCalendar.getTimeInMillis() / 10001;

            if (LOG_ENABLE){
                Log.i(TAG,"X:"+String.valueOf(x));
                Log.i(TAG,"Y:"+String.valueOf(y));
                Log.i(TAG,"Z:"+String.valueOf(z));
            }

            int second = mCalendar.get(Calendar.SECOND);

            int px = Math.abs(posX - x);
            int py = Math.abs(posY - y);
            int pz = Math.abs(posZ - z);
            int maxValue = getMaxValue(px, py, pz);
            if (LOG_ENABLE)Log.d(TAG, "maxValue" + maxValue + " stamp:" + stamp + "lastTimeStamp:" + lastTimeStamp + "diff:"+(stamp - lastTimeStamp));
            if (maxValue > 2 && (stamp - lastTimeStamp) > 30) {
                lastTimeStamp = stamp;
                if (LOG_ENABLE)Log.d(TAG, " sensor is moved or changed...");
                Toast.makeText(this,"运动中",Toast.LENGTH_SHORT).show();
            }

            posX = x;
            posY = y;
            posZ = z;
        }
    }

    public int getMaxValue(int x, int y, int z) {
        int max = 0;
        if (x >= y && x >= z) {
            max = x;
        } else if (y >= x && y >= z) {
            max = y;
        } else if (z >= x && z >= y) {
            max = z;
        }

        return max;
    }
}
