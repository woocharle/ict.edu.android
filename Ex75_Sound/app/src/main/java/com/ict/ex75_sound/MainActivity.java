package com.ict.ex75_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    // 음악파일은 data/data/패키지이름/파일이름
    // 퍼미션 : <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    static final String MP3_URL =
            Environment.getDataDirectory()+"/data/com.ict.ex75_sound/back.mp3";
    Button button1, button2, button3;
    private MediaPlayer player;
    private int playbackPosition = 0 ; // 재 실행시 시작위치를 기억하는 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(player != null){
                        player.release();
                    }
                    player = new MediaPlayer();
                    player.setDataSource(MP3_URL);
                    player.prepare();  // start() 전에 반드시 먼저 할 것
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player != null){
                    playbackPosition = player.getCurrentPosition();
                    player.pause();
                    Toast.makeText(MainActivity.this, "일시 정지", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(player != null && !player.isPlaying()){
                        player.start();
                        player.seekTo(playbackPosition);
                        Toast.makeText(MainActivity.this, "재 실행 중",
                                Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                }
            }
        });
    }

    // 액티비티가 종료되는 순간 처리
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null) player.release();
    }
}