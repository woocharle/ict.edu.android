package com.ict.ex60_db;

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
    EditText edit1, edit2 ;
    Button btn1, btn2, btn3 ;
    TextView txt1;

    // DB처리
    SQLiteDatabase database;
    String table_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        txt1 = findViewById(R.id.txt1);

        // 데이터베이스 만들기
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String db_Name = edit1.getText().toString();
                // 데이터베이스 만들 메소드 실행 (내가 만들기 )
                createDatabase(db_Name);
            }
        });

        // 테이블 만들기
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_Name = edit2.getText().toString();
                // table 만들 메소드 실행 (내가 만들기 )
                createTable(table_Name);

                // 테이블에 레코드 삽입 (내가 만들기 )
                insertRecord();
            }
        });
        // select문
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //select문 실행하는 메소드 (내가 만들기 )
                selectAll();
            }
        });
    }

    private void  createDatabase(String db_Name){
        // 데이터베이스 만들기 :  openOrCreateDatabase("데이터베이스이름", 사용모드, 커서 생성 );
       database = openOrCreateDatabase(db_Name, MODE_PRIVATE, null );
       txt1.append("데이터 베이스 생성됨\n");
    }

    private void createTable(String table_Name){
        if(database == null){
            Toast.makeText(this, "데이터베이스를 먼저 만드세요",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // 테이블 만들기 ( MariaDB, MySQL과 같은 쿼리를 사용한다.)
        // 쿼리 실행 명령 : execSQL(String sql)
        database.execSQL("create table if not exists "+ table_Name + "("
                        + "_idx integer primary key autoincrement, "
                        + "_name text , "
                        + "_age integer, "
                        + "_phone text)");
        txt1.append(table_Name+"테이블 생성됨\n");
    }
    private void insertRecord(){
        if(database == null){
            Toast.makeText(this, "데이터베이스를 먼저 만드세요",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(table_Name == null){
            Toast.makeText(this, "테이블 를 먼저 만드세요",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // 데이터 삽입하기
        database.execSQL("insert into " + table_Name+ " (_name,_age,_phone) "+
                " values ('John', 20, '010-7979-7979')");

        database.execSQL("insert into " + table_Name+ " (_name,_age,_phone) "+
                " values ('hong', 28, '010-1234-7979')");

        database.execSQL("insert into " + table_Name+ " (_name,_age,_phone) "+
                " values ('park', 36, '010-7890-7890')");

        txt1.append("레코드 추가\n");
    }
    private void selectAll(){
        if(database == null){
            Toast.makeText(this, "데이터베이스를 먼저 만드세요",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(table_Name == null){
            Toast.makeText(this, "테이블 를 먼저 만드세요",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // select 문 사용  ; 결과는 Cursor, 실행문 : rawQuery(sql)
        Cursor cursor = database.rawQuery("select * from "+ table_Name, null);
        // java의 resultSet과 같음
        // db 성공 후 위치 확인 : /data/data/패키지이름/databases/mydb01.db
        while(cursor.moveToNext()){
            txt1.append("_idx : " + cursor.getInt(0)+ "\n");
            txt1.append("_name : " + cursor.getString(1)+ "\n");
            txt1.append("_age : " + cursor.getString(2)+ "\n");
            txt1.append("_phone : " + cursor.getString(3)+ "\n");
            txt1.append("======================\n");
        }
    }
}