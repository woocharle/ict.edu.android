package com.ict.ex20_timepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    Button button;
    TextView textView;

    Calendar now = Calendar.getInstance();
    int hour = now.get(Calendar.HOUR_OF_DAY);
    int minute = now.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = findViewById(R.id.button1);
        textView = findViewById(R.id.textView1);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MainActivity2.this, android.R.style.Theme_DeviceDefault, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        textView.setText(String.format("%d : %d", h, m));
                    }
                }, hour, minute, false).show();
            }
        });
    }
}