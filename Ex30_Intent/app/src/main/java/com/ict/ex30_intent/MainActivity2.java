package com.ict.ex30_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 넘어온 인텐트 받기
        Intent intent = getIntent();

        // 별도로 저장후 연산하기
        int s1 = intent.getIntExtra("s1",0);
        int s2 = intent.getIntExtra("s2",0);
        String op = intent.getStringExtra("op");

        // 보내는 인텐트 만들기 (갈곳 지정하지 않음)
        Intent sendIntent = new Intent();
        int res = 0 ;
        if(s2==0 && op.equals("/")){
            sendIntent.putExtra("res","0으로는 나눌수 없습니다.");
            // 실패 했을 때 가는 방법
            setResult(RESULT_CANCELED,sendIntent);
        }else{
            switch (op){
                case "+": res = s1 + s2; break;
                case "-": res = s1 - s2; break;
                case "*": res = s1 * s2; break;
                case "/": res = s1 / s2; break;
            }
            sendIntent.putExtra("res",res);
            setResult(RESULT_OK, sendIntent);
        }
        finish();
    }
}