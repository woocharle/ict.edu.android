package com.ict.ex22_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 옵션 메뉴 : 주메뉴, 메뉴 버튼을 클릭하면 나오는 메뉴
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // 옵션메뉴 만들기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //    groupid, itemid, order, title
        MenuItem item = menu.add(0,0,0,"짜장면");
        menu.add(0,1,0,"짬뽕");
        menu.add(0,2,0,"볶음밥");
        SubMenu sm = menu.addSubMenu("만두");
        sm.add(0,3,0,"물만두");
        sm.add(0,4,0,"군만두");
        return super.onCreateOptionsMenu(menu);
    }
    // 옵션메뉴 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // MenuItem item : 선택된 메뉴가 넘어온다.
        Toast.makeText(this, item.getTitle()+" 선택", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}