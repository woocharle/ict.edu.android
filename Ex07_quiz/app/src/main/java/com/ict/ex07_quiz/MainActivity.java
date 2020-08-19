package com.ict.ex07_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView image1, image2;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_01);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                image1.setImageResource(R.drawable.dream02);
                image2.setImageResource(0);

            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                image1.setImageResource(0);
                image2.setImageResource(R.drawable.dream02);
            }
        });


    }
}