package com.ict.ex59_thead;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView txt1;
    Button button1;
    ProgressBar progressBar1;
    Handler handler = new MainHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt1 = findViewById(R.id.txt1);
        button1 = findViewById(R.id.button1);
        progressBar1 = findViewById(R.id.progressBar1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InnerThread innerThread = new InnerThread();
                innerThread.start();
            }
        });
    }

    // 스레드 처리하는 내부 클래스
    private class InnerThread extends  Thread{
        @Override
        public void run() {
            for (int i=0 ; i<=progressBar1.getMax(); i++){
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                }
                // hadler를 이용해서 메세지를 전달하자
                // 아래에 핸들러를 만들자
                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", i);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }
    }
    // MainHandler를 전역변수로 사용
    private class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            txt1.setText(value + " %");
        }
    }
}