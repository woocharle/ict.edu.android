package com.ict.ex32_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button button2, button3, button4, button11;
    String id, pw, msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button11 = findViewById(R.id.button11);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");

        Toast.makeText(this, id+"님이 접속했습니다.", Toast.LENGTH_SHORT).show();


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_1 = new Intent(MainActivity2.this, MainActivity3.class);
                go_1.putExtra("id", id);
                startActivityForResult(go_1, 1000);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_2 = new Intent(MainActivity2.this, MainActivity4.class);
                go_2.putExtra("id", id);
                startActivityForResult(go_2, 2000);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_3 = new Intent(MainActivity2.this, MainActivity5.class);
                go_3.putExtra("id", id);
                startActivityForResult(go_3, 3000);
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // onActivityResult()를 이용
                Intent go_home = new Intent();
                go_home.putExtra("msg", "메인메뉴에서");
                setResult(RESULT_OK, go_home);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            if(requestCode==RESULT_OK){
                msg = data.getStringExtra("msg");
                Toast.makeText(this, msg+"에서 돌아옴", Toast.LENGTH_SHORT).show();

            }
        }else if(requestCode==2000){
            if(requestCode==RESULT_OK){
                msg = data.getStringExtra("msg");
                Toast.makeText(this, msg+"에서 돌아옴", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode==3000){
            if(requestCode==RESULT_OK){
                msg = data.getStringExtra("msg");
                Toast.makeText(this, msg+"에서 돌아옴", Toast.LENGTH_SHORT).show();
            }
        }

    }
}