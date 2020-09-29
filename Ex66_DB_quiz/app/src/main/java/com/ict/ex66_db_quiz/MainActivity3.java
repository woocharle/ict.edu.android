package com.ict.ex66_db_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity {
    TextView tday;
    EditText editText;
    Button button1, button2, button3;
    String date;
    DAO dao;
    String idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tday = findViewById(R.id.tDay);
        editText = findViewById(R.id.editText5);
        button1 = findViewById(R.id.update);
        button2 = findViewById(R.id.delete);
        button3 = findViewById(R.id.home2);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        tday.setText(date + " 일정");

        dao = DAO.db_open(this);
        final Cursor cursor = dao.select_data(date);
        if(cursor.getCount() == 0){
            button1.setText("저장");
            editText.setHint("스케줄이 존재하지 않습니다.");
        }else{
            button1.setText("수정");
            while(cursor.moveToNext()){
                idx= cursor.getString(0);
                editText.setText(cursor.getString(2));
            }
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button1.getText().equals("저장")){
                    dao.insertData(date, editText.getText().toString().trim());
                    button1.setText("수정");
                }else if(button1.getText().equals("수정")){
                    dao.updateData(idx, editText.getText().toString().trim());
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.deleteData(idx);
                editText.setText("");
                button1.setText("저장");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] c_date = date.split("_");
                int y = Integer.parseInt(c_date[0]);
                int m = Integer.parseInt(c_date[1])-1;
                int d = Integer.parseInt(c_date[2]);

                new DatePickerDialog(MainActivity3.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date = year + "_" + (month+1) + "_" + day;
                        tday.setText(date + " 일정");
                        editText.setText("");

                        Cursor cursor1 = dao.select_data(date);
                        if(cursor1.getCount() == 0){
                            button1.setText("저장");
                            editText.setHint("스케줄이 존재하지 않습니다.");
                        }else{
                            button1.setText("수정");
                            while (cursor1.moveToNext()) {
                                idx = cursor1.getString(0);
                                editText.setText(cursor1.getString(2));
                            }
                        }
                    }
                }, y, m, d).show();
            }
        });

    }
}