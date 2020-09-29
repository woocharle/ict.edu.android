package com.ict.ex44_tabfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment_A fragment_1;
    Fragment_B fragment_2;
    Fragment_C fragment_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 이벤트
        fragment_1 = new Fragment_A();
        fragment_2 = new Fragment_B();
        fragment_3 = new Fragment_C();

        // 첫화면 나오게 하자
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment_1).commit();

        BottomNavigationView bv = findViewById(R.id.bottom_navi);
        // 첫번째 탭 선택 된 상태
        bv.setSelectedItemId(R.id.tab1);

        bv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 메뉴처리 하는 방법과 같음
                switch (item.getItemId()){
                    case R.id.tab1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, fragment_1).commit();
                        return true;
                    case R.id.tab2:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, fragment_2).commit();
                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, fragment_3).commit();
                        return true;
                }
                return false;
            }
        });
    }
}