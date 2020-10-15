package com.ict.ex82_socket2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11;

    Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[11];

        for (int i = 0; i<11; i++){
            int k = getResources().getIdentifier("button"+(i+1), "id", getPackageName());
            buttons[i] = findViewById(k);

        }

        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity1.class);
                startActivity(intent);

            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);

            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);

            }
        });

        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity5.class);
                startActivity(intent);

            }
        });

        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity6.class);
                startActivity(intent);

            }
        });

        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity7.class);
                startActivity(intent);

            }
        });

        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity8.class);
                startActivity(intent);

            }
        });

        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity9.class);
                startActivity(intent);

            }
        });

        buttons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity10.class);
                startActivity(intent);

            }
        });

        buttons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String j = "MainActivity"+ num;
                Intent intent = new Intent(MainActivity.this, MainActivity11.class);
                startActivity(intent);

            }
        });
    }
}