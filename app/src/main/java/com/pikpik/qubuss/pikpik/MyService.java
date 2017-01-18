package com.pikpik.qubuss.pikpik;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by qubuss on 17.01.2017.
 */

public class MyService extends Service {

    private int sleep;

    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Runnable runnable = new Thread(){
        @Override
        public void run() {
            Log.i("MEDIAPLAYER", "run");
            mediaPlayer.start();
            handler.postDelayed(this, sleep*1000);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("MEDIAPLAYER", "onCreate");
        mediaPlayer = MediaPlayer.create(this, R.raw.bleep);
        mediaPlayer.setLooping(false);

    }

    @Override
    public void onDestroy() {
        Log.i("MEDIAPLAYER", "onDestroy");
        mediaPlayer.stop();
        handler.removeCallbacks(runnable);

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MEDIAPLAYER", "onStart");
        Bundle extras = intent.getExtras();
        if(extras != null){
            sleep = extras.getInt("sleep");
            Log.i("MEDIAPLAYER", "sleep: "+ sleep);

            handler.postDelayed(runnable, 0);
        }

        return START_STICKY;
    }


}
