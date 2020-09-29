package com.ict.ex32_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    Button button5, button6;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Toast.makeText(this, id+"님이 접속했습니다.", Toast.LENGTH_SHORT).show();

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_menu = new Intent();
                go_menu.putExtra("msg", "고객화면");
                setResult(RESULT_OK, go_menu);
                finish();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_home
                    = new Intent(MainActivity3.this, MainActivity.class);
                go_home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                go_home.putExtra("msg", "고객화면");
                startActivity(go_home);
                finish();
            }
        });
    }
}