package com.ict.ex23_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // xml에서 메뉴를 만들어서 호출
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 메뉴이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        switch (item.getItemId()){
            case R.id.apple: msg = "사과"; break;
            case R.id.banana: msg = "바나나"; break;
            case R.id.grape: msg = "포도"; break;
            case R.id.mango: msg = "망고"; break;
            case R.id.durio: msg = "두리안"; break;
        }
        Toast.makeText(this, msg+" 선택", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}