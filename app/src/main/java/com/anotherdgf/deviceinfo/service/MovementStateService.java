package com.anotherdgf.deviceinfo.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.WindowManager;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static com.anotherdgf.deviceinfo.constant.LOG_ENABLE;

/**
 * Created by denggaofeng on 2018/5/14.
 */

public class MovementStateService extends Service implements SensorEventListener {

    private final static String TAG = "MovementStateService";
    private final int MOVE = 101;
    private final int STATE_CHANGED = 102;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private int posX = 0;
    private int posY = 0;
    private int posZ = 9;

    private Calendar mCalendar;
    private long lastTimeStamp = 0;

    //定时任务计时器
    private Timer mTimer;
    private TimerTask mTimerTask;
    private Handler handler;
    private AlertDialog dialog;
    private Vibrator vibrator;

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

        vibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);

        //定时器任务
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                switch (msg.what){
                    case MOVE:
                        //震动dialog
                        vibrator.vibrate(1000);
                        showDialog();
                        break;
                    case STATE_CHANGED:
                        //改变计时器状态
                        stopTimer();
                        initTimer();
                        break;
                }
            }
        };

        initTimer();
    }

    private void initTimer(){
        if (null == mTimer){
            mTimer = new Timer();
        }
        if (null == mTimerTask){
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = MOVE;
                    message.obj = System.currentTimeMillis();
                    handler.sendMessage(message);
                }
            };
        }
        if (null != mTimerTask && null != mTimer){
            mTimer.schedule(mTimerTask,3600000,3600000);
        }
    }

    private void stopTimer(){
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("您久坐超过一小时，请活动一下");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
        }
        //暂停定时任务
        mTimer.cancel();
        mTimerTask.cancel();
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
                Message message = new Message();
                message.what = STATE_CHANGED;
                message.obj = System.currentTimeMillis();
                handler.sendMessage(message);
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
