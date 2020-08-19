package com.ict.ex13_rec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar1, ratingBar2, ratingBar3, ratingBar4, ratingBar5;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingBar1 = findViewById(R.id.ratingBar1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        ratingBar4 = findViewById(R.id.ratingBar4);
        ratingBar5 = findViewById(R.id.ratingBar5);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingBar1.setRating(ratingBar1.getRating()+ratingBar1.getStepSize());
                ratingBar2.setRating(ratingBar2.getRating()+ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating()+ratingBar3.getStepSize());
                ratingBar4.setRating(ratingBar4.getRating()+ratingBar4.getStepSize());
                ratingBar5.setRating(ratingBar5.getRating()+ratingBar5.getStepSize());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingBar1.setRating(ratingBar1.getRating()-ratingBar1.getStepSize());
                ratingBar2.setRating(ratingBar2.getRating()-ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating()-ratingBar3.getStepSize());
                ratingBar4.setRating(ratingBar4.getRating()-ratingBar4.getStepSize());
                ratingBar5.setRating(ratingBar5.getRating()-ratingBar5.getStepSize());

            }
        });

        //이벤트 리스너
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(MainActivity.this, "첫번째 줄의 별의 갯수: " + v, Toast.LENGTH_SHORT).show();
            }
        });

    }
}