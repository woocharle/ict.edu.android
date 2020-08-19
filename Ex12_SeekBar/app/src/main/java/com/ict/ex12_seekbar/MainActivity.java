package com.ict.ex12_seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv ;
    SeekBar sb;
    Button btn;
    Handler handler = new Handler();
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tv = findViewById(R.id.tv);
         sb = findViewById(R.id.sb);
         btn = findViewById(R.id.btn);

         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 new Thread(new Runnable() {
                     @Override
                     public void run() {
                         for (i = 0 ; i<= sb.getMax(); i++) {
                             sb.setProgress(i);
                             handler.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     tv.setText("진행률 : " + sb.getProgress() + "%");
                                 }
                             });
                             SystemClock.sleep(500);
                             i = sb.getProgress();
                         }
                     }
                 }).start();
             }
         });
    }
}