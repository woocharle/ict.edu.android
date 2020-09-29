package com.ict.ex32_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {
    Button button9, button10;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        button9 = findViewById(R.id.button7);
        button10 = findViewById(R.id.button8);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Toast.makeText(this, id+"님이 접속했습니다.", Toast.LENGTH_SHORT).show();

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_menu = new Intent();
                go_menu.putExtra("msg", "고객화면");
                setResult(RESULT_OK, go_menu);
                finish();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_home
                        = new Intent(MainActivity5.this, MainActivity.class);
                go_home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                go_home.putExtra("msg", "고객화면");
                startActivity(go_home);
                finish();
            }
        });

    }
}