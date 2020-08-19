package com.ict.ex06_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    Button btn;
    CheckBox chk;
    RadioGroup rg;
    TextView txt1, txt2;
    ImageView img;
    int a = 0;
    int[] images = {R.drawable.boy, R.drawable.coffe, R.drawable.dog, R.drawable.donald} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_01);

        chk = findViewById(R.id.chk);
        rg = findViewById(R.id.rg);
        btn = findViewById(R.id.btn);
        img = findViewById(R.id.img);
        txt2 = findViewById(R.id.txt2);

        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    txt2.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                    img.setVisibility(View.VISIBLE);

                }else{
                    txt2.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    btn.setVisibility(View.INVISIBLE);
                    img.setVisibility(View.INVISIBLE);
                    rg.clearCheck();
                    img.setImageResource(0);
                }
           }
        });

        /*
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1 : a = 0; break;
                    case R.id.rb2 : a = 1; break;
                    case R.id.rb3 : a = 2; break;
                    case R.id.rb4 : a = 3; break;
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageBitmap(BitmapFactory.decodeResource(getResources(),images[a]));

            }
        });
        */

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.rb1 : img.setImageResource(R.drawable.boy);break;
                    case R.id.rb2 : img.setImageResource(R.drawable.coffe);break;
                    case R.id.rb3 : img.setImageResource(R.drawable.dog);break;
                    case R.id.rb4 : img.setImageResource(R.drawable.donald);break;
                    default:
                        Toast.makeText(MainActivity.this, "제대로 눌러주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}