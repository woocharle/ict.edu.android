package com.ict.ex05_imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1, imageView2, imageView3, imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_01);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        imageView1.getLayoutParams().width = 400;
        imageView1.getLayoutParams().height = 400;

        imageView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "이미지를 눌렀네요.", Toast.LENGTH_SHORT).show();
            }
        });

        imageView2.setVisibility(View.VISIBLE);
        imageView3.setVisibility(View.VISIBLE);

        //imageView는 해당 이미지를 연결하는 속성이 없다.
        //자바에서

        //imageView4.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.coffe));
        imageView4.setImageResource(R.drawable.coffe);
    }

}