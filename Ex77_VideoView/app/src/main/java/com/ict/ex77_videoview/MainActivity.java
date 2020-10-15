package com.ict.ex77_videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);

        MediaController controller = new MediaController(this);
        controller.setAnchorView(videoView);
        /*
        videoView.setVideoURI(
                Uri.parse(
                        Environment.getDataDirectory()+"/data/"+getPackageName()+"/video.mp4"));
        */
        // R.raw에 video.mp4를 넣고 실행하기
        videoView.setVideoURI(
                Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video));
        videoView.requestFocus();
        videoView.start();
    }
}