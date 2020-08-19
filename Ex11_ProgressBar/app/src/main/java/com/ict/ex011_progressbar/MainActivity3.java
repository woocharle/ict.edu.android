package com.ict.ex011_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView txt1, txt2;
    ProgressBar prgBar1, prgBar2;
    Button btn4;
    Handler handle = new Handler();

    int i = 0;
    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txt1 = findViewById(R.id.txt5);
        txt2 = findViewById(R.id.txt6);
        prgBar1 = findViewById(R.id.prgBar1);
        prgBar2 = findViewById(R.id.prgBar2);
        btn4 = findViewById(R.id.btn4);

        btn4.setOnClickListener(new View.OnClickListener() {
            // 스레드 처리시 뷰(textView)에 직접 데이터를 수정할 수 있다.
            // 핸들러를 이용해서 수정해야한다.
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(i = 0; i <= 100; i++){
                            handle.post(new Runnable() {
                                @Override
                                public void run() {
                                    prgBar1.setProgress(i);
                                    txt1.setText(prgBar1.getProgress()+"%");

                                }
                            });
                            SystemClock.sleep(100);
                        }

                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(j = 0; j <= 100; j++){
                            handle.post(new Runnable() {
                                @Override
                                public void run() {
                                    prgBar2.setProgress(j);
                                    txt2.setText(prgBar2.getProgress()+"%");

                                }
                            });
                            SystemClock.sleep((int)(Math.random()*200));
                        }

                    }
                }).start();
            }

        });

    }

}