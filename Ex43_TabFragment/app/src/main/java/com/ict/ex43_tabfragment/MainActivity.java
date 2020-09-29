package com.ict.ex43_tabfragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    // 프레그먼트
    Fragment_A fragment_1 ;
    Fragment_B fragment_2 ;
    Fragment_C fragment_3 ;
    // 툴바 :  import androidx.appcompat.widget.Toolbar
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 툴바 설정
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 액션바
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        // 탭바
        TabLayout tabs = findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setText("국내(K-POP"));
        tabs.addTab(tabs.newTab().setText("해외(POP)"));
        tabs.addTab(tabs.newTab().setText("가수별"));

        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_dialog_email));
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_dialog_map));
        //  만든 그림 사용 : tabs.addTab(tabs.newTab().setIcon(R.drawable.burger));
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_dialog_dialer));

        // 이벤트 처리
        fragment_1 = new Fragment_A();
        fragment_2 = new Fragment_B();
        fragment_3 = new Fragment_C();

        // 처음 화면에 나오는 것
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.container,fragment_1).commit();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0: getSupportFragmentManager()
                            .beginTransaction().replace(R.id.container,fragment_1).commit(); break;
                    case 1: getSupportFragmentManager()
                            .beginTransaction().replace(R.id.container,fragment_2).commit(); break;
                    case 2: getSupportFragmentManager()
                            .beginTransaction().replace(R.id.container,fragment_3).commit(); break;
                    case 3: getSupportFragmentManager()
                            .beginTransaction().replace(R.id.container,fragment_1).commit(); break;
                    case 4: getSupportFragmentManager()
                            .beginTransaction().replace(R.id.container,fragment_2).commit(); break;
                    case 5: getSupportFragmentManager()
                            .beginTransaction().replace(R.id.container,fragment_3).commit(); break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }
}