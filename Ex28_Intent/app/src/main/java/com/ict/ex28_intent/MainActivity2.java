package com.ict.ex28_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    Button button2, button3, button5, button6, button7;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);

        //잘못된 코드 1
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity2.this, MainActivity.class);

                startActivity(intent);

            }
        });

        //잘못된 코드 2
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity2.this, MainActivity.class);
                //플러그 삽입
                //현재 인텐트가 맨 위로 있으면서 다른 액티비티들은 모두 삭제. (보통 홈버튼에서 사용)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // => Home button에서 많이 사용.
                startActivity(intent);

            }
        });

        //잘못된 코드
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity2.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity2.this, MainActivity2.class);

                //맨 위에서 자기자신을 다시 호출 할 때 새로 만들어지지 않도록 하는 플러그 데이터는 전달가능하다.
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //데이터값 전달.
                intent.putExtra("name", "태권브이");
                intent.putExtra("age", "47");



                startActivity(intent);
            }
        });

        //전달된 데이터 받기


    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);
        Toast.makeText(this, name +","+ age, Toast.LENGTH_SHORT).show();

    }

}
