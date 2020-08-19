package com.ict.ex10_videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity2 extends AppCompatActivity {
    VideoView videoView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MediaController controller = new MediaController(this);
        controller.setAnchorView(videoView2);
        //videoView2.setMediaController(new MediaController(this));
        //videoView2.setVideoURI(Uri.parse("android:resource://"+getPackageName()+"/"+R.raw.video));
        /*
        videoView2.setVideoURI(
            Uri.parse(Environment.getDataDirectory()+"/data/"+getPackageName()+"/video.mp4")
        );
        */
        videoView2.setVideoURI(Uri.parse("android:resource://"+getResources().getResourcePackageName(R.raw.video)));

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView2.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(videoView2 != null && videoView2.isPlaying()) videoView2.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView2 != null){
            videoView2.stopPlayback();
        }
    }
}