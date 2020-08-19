package com.ict.ex29_intent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent
                        = new Intent(MainActivity.this, MainActivity2.class);

                //데이터 저장 => 부가데이터(Extra Data)
                intent.putExtra("name", "김선달");
                intent.putExtra("age", 44);
                intent.putExtra("gender", true);

                String[] person ={"김구", "1876", "true"};
                intent.putExtra("person", person);

                startActivity(intent);



            }
        });
    }
    // 사용 불가



}