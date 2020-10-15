package com.ict.ex40_fregment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// 1. API 28부터는 fragment를 가지고 있는 Activity는 FragmentActivity를 사용해야 된다.
// 현재 API를 확인 하는 방법 : 프로젝트- Gradle Scripts - 두번째 build.gradle 더블 클릭
// 2. layout에서 만든 fragment_a, b를 인플레이 해주는 Fragment를 만들어야 한다.
public class MainActivity extends FragmentActivity {
    Button btn1, btn2, btn3 ;
    // Fragment_A,B를 객체로 만들어서 사용하자
    Fragment_A frag_a ;
    Fragment_B frag_b ;
    Fragment_C frag_c ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        // fragment_a 호출
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag_a = new Fragment_A();
                // 프레그먼트 매니져를 이용해서 프레그먼트 내용 변경
                //  remove (삭제),  add(추가)
                // replace (치환)
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_lay,frag_a).commit();
            }
        });
        // fragment_b호출
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag_b = new Fragment_B();
                // 프레그먼트 매니져를 이용해서 프레그먼트 내용 변경
                // remove (삭제),  add(추가)
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(frag_b).commit();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_lay, frag_b).commit();
            }
        });
        // fragment_c 호출
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag_c = new Fragment_C();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_lay,frag_c).commit();
            }
        });
    }
}