package com.ict.ex57_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    EditText name, age;
    Button btn1, btn2 ;

    String birth;
    int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        Calendar now = Calendar.getInstance();
        mYear = now.get(Calendar.YEAR);
        mMonth = now.get(Calendar.MONTH);
        mDay = now.get(Calendar.DATE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity2.this,
                        android.R.style.Theme_DeviceDefault_Dialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                                   mYear = y;
                                   mMonth = m ;
                                   mDay = d;
                                   birth = String.format("%d. %d. %d. 선택", y, m+1, d);
                                Toast.makeText(MainActivity2.this, birth, Toast.LENGTH_SHORT).show();
                            }
                        },mYear, mMonth, mDay).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mName = name.getText().toString();
                String mAge = age.getText().toString();
                Toast.makeText(MainActivity2.this, "이름 : "+mName+"\n나이 : "+ mAge
                        +"\n생년월일 : "+birth, Toast.LENGTH_SHORT).show();
                finish();
                // 애니메이션 적용
                overridePendingTransition(R.anim.ret, R.anim.return_back);
            }
        });
    }
}