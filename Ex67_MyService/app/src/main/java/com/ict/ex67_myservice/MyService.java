package com.ict.ex67_myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

// 서비스 클래스를 만들기 위해서는 반드시 Service를 상속 받아야 한다.
// 메니페스트에 등록해야 한다.
// 3가지 메소드를 호출 해서 사용한다.
// MainActivity에서 startService(intent)를 시행하면
// 서비스 클래스에서 onCreate() =>  onStartCommand() 실행됨
// 서비스를 종료할때 onDestroy() 를 실행한다.


public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("my", "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("my", "onStartCommand()");
        processCommand(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    public void processCommand(Intent intent){
        String msg = intent.getStringExtra("msg");

        try{
            Thread.sleep(5000);
        }catch (Exception e){

        }

        // MainActivity(메모리에 존재하는 Activity)로
        // 갈 경우 MainActivity의 onNewIntent()로 간다.
        // Intent goIntent = new Intent(getApplicationContext(), MainActivity.class);

        // MainActivity2(메모리에 존재하지 않는 Activity)로
        // 갈 경우  MainActivity2의 onCreate()로 간다.

        Intent goIntent = new Intent(getApplicationContext(), MainActivity2.class);

        goIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        goIntent.putExtra("msg", msg + "는 from myservice");
        startActivity(goIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("my", "onDestroy()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
