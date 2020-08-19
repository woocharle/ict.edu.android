package com.ict.ex24_menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        // 팝업메뉴
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String msg = "";
                        switch (menuItem.getItemId()){
                            case R.id.apple: msg = "사과"; break;
                            case R.id.banana: msg = "바나나"; break;
                            case R.id.grape: msg = "포도"; break;
                            case R.id.mango: msg = "망고"; break;
                            case R.id.durio: msg = "두리안"; break;
                        }
                        Toast.makeText(MainActivity.this, msg + " 선택", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("종료메세지")
                        .setMessage("정말 종료 할까요?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("아니오",null)
                        .show();
            }
        });
    }
}