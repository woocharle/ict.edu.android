package com.ict.ex33_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity2 extends AppCompatActivity {
    RatingBar[] rb = new RatingBar[9];
    int[]ratingID = {R.id.ratingBar1, R.id.ratingBar2, R.id.ratingBar3,
            R.id.ratingBar4, R.id.ratingBar5, R.id.ratingBar6,
            R.id.ratingBar7, R.id.ratingBar8, R.id.ratingBar9};
   Button button2, button3 ;
   int[] count ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        // 넘어온 인텐트 받기
        final Intent intent = getIntent();
        count = intent.getIntArrayExtra("count");

        // count 가지고 별을 찍자
        for (int i = 0 ; i<count.length; i++){
            rb[i] = findViewById(ratingID[i]);
            rb[i].setRating(count[i]);
        }

        // 되돌아 가기
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.putExtra("msg","두번째 화면");
                setResult(RESULT_OK, intent1);
                finish();
            }
        });

        // 점수 중 최대값을 구해서 다음페이지로 이동
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1
                        = new Intent(MainActivity2.this, MainActivity3.class);
                int max = 0 ;   // 점수 비교
                int index = 0 ; // 가장큰 점수의 위치값
                for(int i=0; i<count.length; i++){
                    if(max < count[i]){
                        max = count[i];
                        index = i ;
                    }
                }

                intent1.putExtra("index", index);
                startActivity(intent1);

            }
        });
    }
}









