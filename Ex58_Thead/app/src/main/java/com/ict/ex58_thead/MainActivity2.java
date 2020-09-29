package com.ict.ex58_thead;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textView1;
    Button button1;
    Handler handler = new MainHandler();
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

                BackgoundThred bt = new BackgoundThred();
                bt.start();
            }
        });
    }
    // 스레드 처리하는 내부 클래스
    private class BackgoundThred extends Thread{
        @Override
        public void run() {
            for(int i=1 ; i<21; i++){
                // 핸들러를 사용하지 않으면 오류 발생
                // textView1.setText(i);

                // 너무 빨라서 1초에 한번씩 실행하게 만듦
                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }
                Message msg = handler.obtainMessage();

                // 마치 인텐트에서 데이터 전송하는 것과 같음
                Bundle bundle = new Bundle();
                bundle.putInt("value", i);
                msg.setData(bundle);

                handler.sendMessage(msg);
            }
        }
    }
    // Handler 처리 하는 클래스를 하나 만들자
    private class MainHandler extends Handler{
        // handler.sendMessage(msg)에서 보낸 정보를 받아 처리 하기 위해서 override 하자.
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView1.setText("value 값 : " + value);
        }
    }

}