package com.ict.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // - 화면을 구성하는 xml파일을 불러와서 화면을 보여주는 역할
        // - R은 안드로이드에서 사용자가 만들어놓은 모든 것을 저장하는 클래스
        // - 저장된 메소드, 변수와 상수들은 전부 static이 된다.
    }
}