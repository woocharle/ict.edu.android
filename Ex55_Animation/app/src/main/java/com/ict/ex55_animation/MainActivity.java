package com.ict.ex55_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

// 트윈 애니메이션 : res-anim - 폴더를 만들어서 xml 소스를 이용해 간단한 애니메이션 작업
public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6;
    ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils
                        .loadAnimation(getApplicationContext(),R.anim.scale);
                imageView1.startAnimation(animation);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils
                        .loadAnimation(getApplicationContext(),R.anim.scale2);
                imageView1.startAnimation(animation);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils
                        .loadAnimation(getApplicationContext(),R.anim.rotate);
                imageView1.startAnimation(animation);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils
                        .loadAnimation(getApplicationContext(),R.anim.rotate2);
                imageView1.startAnimation(animation);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils
                        .loadAnimation(getApplicationContext(),R.anim.translate);
                imageView1.startAnimation(animation);
            }
        });

        // 프레임 애니메이션션
       // animation-list 만들어서 사용
        // res - drawable - xml를 만들어서 사용
        final AnimationDrawable drawable = (AnimationDrawable)imageView2.getDrawable();
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawable.start();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawable.stop();
                // 이벤트 처리
                if(drawable.getCurrent() == drawable.getFrame(0)){
                    Toast.makeText(MainActivity.this, "가위선택", Toast.LENGTH_SHORT).show();
                }else if(drawable.getCurrent() == drawable.getFrame(1)){
                    Toast.makeText(MainActivity.this, "바위선택", Toast.LENGTH_SHORT).show();
                }else if(drawable.getCurrent() == drawable.getFrame(2)){
                    Toast.makeText(MainActivity.this, "보선택", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}