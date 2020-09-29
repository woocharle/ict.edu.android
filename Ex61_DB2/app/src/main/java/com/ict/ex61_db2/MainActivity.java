package com.ict.ex61_db2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView result;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        result = findViewById(R.id.result);
        database = openOrCreateDatabase("mydb02.db", MODE_PRIVATE, null);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(database == null){
                    Toast.makeText(MainActivity.this, "데이터베이스가 없네요", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 테이블 처리 나중
                // select문
                try {
                    Cursor cursor = database.rawQuery("select * from member02 order by _idx", null);

                    while (cursor.moveToNext()){
                        result.append(cursor.getInt(0)+"\t");
                        result.append(cursor.getString(1)+"\t");
                        result.append(cursor.getString(2)+"\t");
                        result.append(cursor.getString(3)+"\t");
                        result.append(cursor.getString(4)+"\n");
                    }
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "제대로 입력하세요", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}