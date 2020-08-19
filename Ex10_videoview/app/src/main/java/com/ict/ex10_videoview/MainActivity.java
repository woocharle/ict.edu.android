package com.ict.ex10_videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv = findViewById(R.id.videoView);

        //사이트 가져온 동영상 보여주기
        //사용자허가를 받아야 한다.(커미션)

        Uri videoUri = Uri.parse("https://www.w3schools.com/html/mov_bbb.mp4");

        vv.setMediaController(new MediaController(this));

        vv.setVideoURI(videoUri);

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                vv.start();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        // 비디오 일시정지
        if(vv != null && vv.isPlaying()) vv.pause();
    }
    // 액티비티가 메모리에서 사라질때
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(vv!=null) vv.stopPlayback();
    }

}