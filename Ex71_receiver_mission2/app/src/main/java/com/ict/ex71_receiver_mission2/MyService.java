package com.ict.ex71_receiver_mission2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = intent.getStringExtra("msg");

        //리시버에 넘길 인텐트 만들어서 처리하기
        Intent go_intent = new Intent(); //동적 리시버
        go_intent.setAction("Test");
        go_intent.putExtra("msg", msg);

        //리시버 호출
        sendBroadcast(go_intent);

        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
