package com.ict.ex58_thead;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    Button button1;
    Handler handler = new Handler();
    int value = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // for (int i = 1; i<21; i++){
                //       직접적으로 View에 데이터를 넣을 수 없다.( 스레드 처리(handler를 사용) 해야 된다.)
                //       textView1.setText(i);
                // }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=1; i<21; i++){
                            value = i ;
                            try {
                                Thread.sleep(1000);
                            }catch (Exception e){
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView1.setText("value 값 : " + value);
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

}