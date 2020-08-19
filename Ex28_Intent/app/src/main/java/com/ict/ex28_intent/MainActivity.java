package com.ict.ex28_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Intent
//1.명시적 인텐트: 이동하려는 곳을 명확하게 명시하는 것.
//2.묵시적 인텐트: 이동하려는 것이 안드로이드 기능으로 명시할 필요가 없을 때 사용.
//화면 전환: intent
//1. 단순화면전환: 현재화면에서 다른 화면으로 이동 (Activity => Activity)

public class MainActivity extends AppCompatActivity {
    Button button1, button4, button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button4 = findViewById(R.id.button4);


        // 잘못된 코드
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // 올바른 코드
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                //현재 화면을 다시 해당화면을 호출하면 중간의 화면들은 다 사라진다.
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

    }
}