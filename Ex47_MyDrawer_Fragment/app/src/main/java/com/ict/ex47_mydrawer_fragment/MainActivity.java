package com.ict.ex47_mydrawer_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Fragment_A fragment_1;
    Fragment_B fragment_2;
    Fragment_C fragment_3;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 이벤트
        fragment_1 = new Fragment_A();
        fragment_2 = new Fragment_B();
        fragment_3 = new Fragment_C();

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // 메뉴처리하는 방법과 같음
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu1){
                    toolbar.setTitle("Camera");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment_1).commit();
                    drawerLayout.closeDrawers();
                }else if(item.getItemId() == R.id.menu2){
                    toolbar.setTitle("갤러리");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment_2).commit();
                    // Drawer 닫기
                    drawerLayout.closeDrawers();
                }else if(item.getItemId() == R.id.menu3){
                    toolbar.setTitle("통화목록");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment_3).commit();
                    drawerLayout.closeDrawers();
                }
                return false;
            }
        });
    }
}