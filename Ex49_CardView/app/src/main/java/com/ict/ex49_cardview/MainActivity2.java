package com.ict.ex49_cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView title ,content;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        title = findViewById(R.id.title);
        img = findViewById(R.id.img);
        content = findViewById(R.id.content);

        // MainActivity 에서 넘오는 인텐트를 받자
        Intent intent = getIntent();
        String r_title = intent.getStringExtra("title");
        int r_imgId = intent.getIntExtra("img",0);
        String r_conent_1 = intent.getStringExtra("content_1");
        String r_conent_2 = intent.getStringExtra("content_2");

        // 해당 위치에 장작하자
        title.setText(r_title);
        img.setImageResource(r_imgId);
        content.append("상품내용_1 : "+r_conent_1+"\n");
        content.append("상품내용_2 : "+r_conent_2+"\n");
    }
}