package com.ict.ex29_intent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //액티비티를 생성해서 넘어온 인텐트 받기

        Intent r_intent = getIntent();

        String name = r_intent.getStringExtra("name");
        int age = r_intent.getIntExtra("age", 0);
        boolean gender = r_intent.getBooleanExtra("gender", false);

        Toast.makeText(this, name + "\n" + age +"\n" + gender , Toast.LENGTH_SHORT).show();

        //배열 처리

        String[] person = r_intent.getStringArrayExtra("person");

        // 여러개의 문자열을 하나로 합침.
        StringBuffer sb = new StringBuffer();
        for (String k : person){
            sb.append(k + "\n");
        }

        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();

    }

    /*
    일반 인텐트는 액티비트를 새로 만들어서 호출한다.
    이때는 onCreate를 사용해야하고
    onCreate()가 사용할 수 없는 기존 인텐트를 호출할 때
    onNewIntent()를 사용한다.

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String name = intent.getStringExtra("name");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

    }
    */


}