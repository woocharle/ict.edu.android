package com.ict.ex20_timepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    TextView textView;
    TextClock textClock1, textClock2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.timepicker);
        textView = findViewById(R.id.textView);
        textClock1 = findViewById(R.id.textclock1);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int h, int m) {
                textView.setText(String.format("%d : %d ", h, m));

            }
        });

        textClock1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

    }
}