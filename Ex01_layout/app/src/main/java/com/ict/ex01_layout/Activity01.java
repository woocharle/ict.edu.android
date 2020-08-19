package com.ict.ex01_layout;

//Activity 컴포넌트: 화면을 구성하는 것. Activity를 반드시 상속 받아야 한다.
//

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity01 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout01);


    }
}
