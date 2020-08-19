package com.ict.ex05_imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity03 extends AppCompatActivity {
    Button button;
    ImageView imageView;
    int[] images = {R.drawable.dream01, R.drawable.coffe, R.drawable.dog, R.drawable.donald} ;
    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_03);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageview);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                imageView.setImageResource(images[a++%4]);

            }
        });
    }
}