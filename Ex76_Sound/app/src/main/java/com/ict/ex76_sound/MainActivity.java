package com.ict.ex76_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   Button button1, button2;
   NotificationManager manager ;  // 통지메니져
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // 음악재생을 누르면  Service가 실행되서 음악을 실행한다.(노티생성)
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 서비스 등록 해야 됨
                // 서비스를 이용(Intent)해서 음악재생
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);

                // 노티 생성
                Notification.Builder builder
                        = new Notification.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("음악서비스");
                builder.setContentText("백지영... 총 맞는 것 처럼");

                // PendingIntent는 다른 애플리케이션에게 인텐트를 위임하는 것을 말한다.
                // 아래 인텐트는 펜딩인텐트에 필요한 intent이다.
                Intent p_intent =
                        new Intent(MainActivity.this, MainActivity.class);
                Intent[] intents = new Intent[1] ;
                intents[0] = p_intent;

                PendingIntent pendingIntent =
                        PendingIntent.getActivities(MainActivity.this, 100, intents, 0);
                builder.setContentIntent(pendingIntent);
                Notification noti = builder.build();
                manager.notify(1, noti);
            }
        });
        // 음악 중지을 누르면 음악을 실행한 Service가 중지된다.(노티제거)
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                    new Intent(MainActivity.this, MyService.class);
                stopService(intent);
                manager.cancel(1);
            }
        });
    }
}