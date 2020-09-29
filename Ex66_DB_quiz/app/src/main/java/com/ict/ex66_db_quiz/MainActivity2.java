package com.ict.ex66_db_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText editText1, editText2, editText3, editText4;
    Button button1, button2;
    String date;
    DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        button1 = findViewById(R.id.button5);
        button2 = findViewById(R.id.button6);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        String[] date2 = date.split("_");

        editText1.setText(date2[0]);
        editText2.setText(date2[1]);
        editText3.setText(date2[2]);

        dao = DAO.db_open(this);

        selectDate();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.insertData(date, editText4.getText().toString().trim());
                selectDate();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void selectDate(){
        Cursor cursor = dao.select_data(date);
        if(cursor.getCount()==0){
            button1.setEnabled(true);
            editText4.setHint("오늘 스케줄 없음");
            Toast.makeText(this, "오늘 스케줄 없음", Toast.LENGTH_SHORT).show();
        } else{
            while(cursor.moveToNext()){
                editText4.setText(cursor.getString(2));
                button1.setEnabled(false);
                editText4.setEnabled(false);
            }
        }
    }


}