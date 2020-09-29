package com.ict.ex64_db;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id, name, phone, addr;
    Button button1, button2, button3, button4, button5;
    TextView result;
    MyDatabase mydb = new MyDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.editText1);
        name = findViewById(R.id.editText2);
        phone = findViewById(R.id.editText3);
        addr = findViewById(R.id.editText4);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        result = findViewById(R.id.result);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAll();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().length() > 0 && name.getText().length() > 0 &&
                        phone.getText().length() > 0 && addr.getText().length() > 0){
                    SQLiteDatabase database = mydb.getWritableDatabase();
                    String sql = "insert into p_list values(null, ?, ?, ?, ?)";
                    String[] arr = {id.getText().toString(), name.getText().toString(),
                            phone.getText().toString(), addr.getText().toString()};
                    database.execSQL(sql, arr);
                    mydb.close();

                    id.setText("");
                    name.setText("");
                    phone.setText("");
                    addr.setText("");

                    selectAll();
                } else {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = mydb.getWritableDatabase();
                String sql = "select * from p_list where id = ? ";
                String[] arr = {id.getText().toString()};
                Cursor cursor = database.rawQuery(sql, arr);
                if(cursor.getCount() == 0){
                    Toast.makeText(MainActivity.this,
                            "찾는 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    button4.setEnabled(true);
                    button5.setEnabled(true);

                    while (cursor.moveToNext()) {
                        name.setText(cursor.getString(2));
                        phone.setText(cursor.getString(3));
                        addr.setText(cursor.getString(4));
                    }
                    cursor.close();
                    mydb.close();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = mydb.getWritableDatabase();
                String sql = "update p_list set name=?, phone=?, addr=?";
                String[] arr = {name.getText().toString(), id.getText().toString(),
                            phone.getText().toString(), addr.getText().toString()};
                database.execSQL(sql, arr);
                mydb.close();
                id.setText("");
                name.setText("");
                phone.setText("");
                addr.setText("");
                selectAll();
                button4.setEnabled(false);
                button5.setEnabled(false);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = mydb.getWritableDatabase();
                String sql = "delete from p_list where id = ? ";
                String[] arr = {id.getText().toString()};
                database.execSQL(sql, arr);
                mydb.close();

                id.setText("");
                name.setText("");
                phone.setText("");
                addr.setText("");
                selectAll();
                button4.setEnabled(false);
                button5.setEnabled(false);
            }
        });

    }

    public void selectAll(){
        SQLiteDatabase database = mydb.getWritableDatabase();
        String sql = "select * from p_list order by idx ";
        Cursor cursor = database.rawQuery(sql, null);
        StringBuffer sb = new StringBuffer();
        while(cursor.moveToNext()){
            String idx = cursor.getString(0);
            String id = cursor.getString(1);
            String name = cursor.getString(2);
            String phone = cursor.getString(3);
            String addr = cursor.getString(4);
            sb.append(idx+", "+ id+", "+name+", "+phone+", "+addr+"\n");
        }
        result.setText(sb.toString());
        cursor.close();
        mydb.close();
    }

}