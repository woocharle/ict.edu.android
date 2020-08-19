package com.ict.ex011_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity2 extends AppCompatActivity {
    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        progressBar = findViewById(R.id.progressBar3);
        button = findViewById(R.id.button);

        //프로그래스바는 기본적으로 스레드 처리를 해줘야한다.
        //아래코딩은 먹통이 된다.
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i<100; i++){
                    progressBar.setProgress(i);
                    SystemClock.sleep(500);
                }
            }
        });
        */
        // 스레드 처리

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i <= 100; i++){
                            progressBar.setProgress(i);
                            SystemClock.sleep(100);
                        }

                    }
                }).start();

            } // => 안드로이드 스레드 처리법.
        });


    }
}