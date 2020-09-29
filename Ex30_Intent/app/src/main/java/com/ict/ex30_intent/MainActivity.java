package com.ict.ex30_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //3. 화면전환 : 정보를 보내고 결과 받기 : 양방향인텐트
                Intent intent
                        = new Intent(MainActivity.this, MainActivity2.class);
                // 정보를 보내고 나고 결과를 받자
                intent.putExtra("s1", 10) ;
                intent.putExtra("s2", 0);
                intent.putExtra("op", "/");

                // startActivity(intent); 정보를 보내기만 하는 메소드
                // 반드시 받는 메소드가 별도로 있다.
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // requestCode : 보낼때 부여한 코드
        // resultCode : 응답결과를 가지고 있다.
        // data : 다른페이지에서 보낸 인텐트
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                textView1.append("\n성공 : "+data.getIntExtra("res",0));
            }else if(resultCode == RESULT_CANCELED){
                textView1.append("\n실패 : " + data.getStringExtra("res"));
            }
        }
    }
}