package com.ict.ex04_button;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1;
    ImageButton btn2;
    CheckBox chk1, chk2;
    RadioGroup rg;
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_01);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        rg = findViewById(R.id.rg);
        txt1 = findViewById(R.id.txt1);
        txt1.setText("대한민국");

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                txt1.setText("서울(Seoul)");
            }
        });

        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //선택하면 true, 해제되면 false
                if(b){
                    txt1.setTextColor(Color.GREEN);
                }else{
                    txt1.setTextColor(Color.DKGRAY);
                }
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    txt1.setTextSize(20);
                }else{
                    txt1.setTextSize(40);
                }
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rg_1: txt1.setTextColor(Color.RED);  break;
                    case R.id.rg_2: txt1.setTextColor(Color.BLUE); break;
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}