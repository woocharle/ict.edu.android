package com.ict.ex04_button;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity02 extends AppCompatActivity {

    ToggleButton tb1;
    Switch sw;
    TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_02);

        tb1 = findViewById(R.id.tb1);
        sw = findViewById(R.id.sw);
        txt2 = findViewById(R.id.txt2);

        tb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    txt2.setTextColor(Color.CYAN);
                }else{
                    txt2.setTextColor(Color.DKGRAY);
                }
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){

                }else{

                }
            }
        });

    }


}