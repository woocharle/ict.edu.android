package com.ict.ex63_db;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button button1, button2, button3, button4;
    TextView result;

    MyDatabase myDatabase;
    SQLiteDatabase database;
    String tName;
    String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.editText1);
        edit2 = findViewById(R.id.editText2);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        result = findViewById(R.id.result);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dbName = edit1.getText().toString();
                myDatabase = new MyDatabase(MainActivity.this, dbName);
                database = myDatabase.getWritableDatabase();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tName = edit2.getText().toString();
                sql = "create table if not exists "+ tName + "("  +
                        "idx integer primary key autoincrement, " +
                        "name text, " +
                        "age  Integer, " +
                        "phone text, " +
                        "reg  timestamp)";
                database.execSQL(sql);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sql = "insert into " + tName
                        +" (name, age, phone, reg) "
                        +" values "
                        +" ('gil dong' , 24, '010-7979-9999', date('now')) ";

                database.execSQL(sql);

                sql = "insert into " + tName
                        +" (name, age, phone, reg) "
                        +" values "
                        +" ('둘리' , 14, '010-9999-9999', date('now')) ";
                database.execSQL(sql);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor
                        = database.rawQuery("select * from " + tName , null);
                while(cursor.moveToNext()){
                    String idx = cursor.getString(0);
                    String name = cursor.getString(1);
                    String age = cursor.getString(2);
                    String phone = cursor.getString(3);
                    String reg = cursor.getString(4);
                    result.append("레코드 : " + idx+", " + name +", " +age +", "+
                            phone+", "+ reg +"\n");
                }
            }
        });


    }
}