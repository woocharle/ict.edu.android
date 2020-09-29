package com.ict.ex62_db3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, age, phone;
    Button button1;
    TextView result;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edit1);
        age = findViewById(R.id.edit2);
        phone = findViewById(R.id.edit3);
        button1 = findViewById(R.id.button1);
        result = findViewById(R.id.result);

        database = openOrCreateDatabase("mydb03.db", MODE_PRIVATE, null);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // 삽입
                    result.setText("");
                    database.execSQL("insert into member03 (_name, _age, __phone, _reg) values " +
                                    "('" + name.getText().toString() + "', '" + age.getText().toString() +
                                    "', '" + phone.getText() + "', date('now'))");

                    // select문
                    Cursor cursor = database.rawQuery("select * from member03 order by _idx", null);
                    while(cursor.moveToNext()){
                        result.append(cursor.getString(0)+"\t");
                        result.append(cursor.getString(1)+"\t");
                        result.append(cursor.getString(2)+"\t");
                        result.append(cursor.getString(3)+"\t");
                        result.append(cursor.getString(4)+"\n");
                    }
                    name.setText("");
                    age.setText("");
                    phone.setText("");

                } catch (Exception e){
                    name.setText("");
                    age.setText("");
                    phone.setText("");
                    Toast.makeText(MainActivity.this, "데이터 테이블이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}