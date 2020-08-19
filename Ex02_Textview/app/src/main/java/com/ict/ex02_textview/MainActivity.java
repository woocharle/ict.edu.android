package com.ict.ex02_textview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_01);

        //layout에 존재하는 textView들을 자바에서 사용하기 위해 인지하기
        // fineViewById
        TextView tv1 = findViewById(R.id.textView1);

        String msg = tv1.getText().toString();
        //System.out.println(msg);

        // Toast를 이용해서 정보 확인하기
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        //가지고 있는 정보 변경
        tv1.setText("안녕하세요");

        //textView2를 고치자
        TextView tv2 = findViewById(R.id.textView2);
        tv2.setText("Android");
        tv2.setTextColor(Color.RED);
        tv2.setTextSize(50.0f);
    }
}