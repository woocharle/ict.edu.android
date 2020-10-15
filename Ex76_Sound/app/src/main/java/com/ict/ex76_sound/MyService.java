package com.ict.ex76_sound;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    MediaPlayer player;
    static final String MP3_URL =
            Environment.getDataDirectory()+"/data/com.ict.ex76_sound/back.mp3";
    public MyService() {}
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    // 서비스 실행
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
             if(player != null){
                    player.release();
                }
                player = new MediaPlayer();
                player.setDataSource(MP3_URL);
                player.prepare();
                player.start();
        }catch (Exception e){
        }
        return super.onStartCommand(intent, flags, startId);
    }
    // 서비스 종료
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
    }
}
