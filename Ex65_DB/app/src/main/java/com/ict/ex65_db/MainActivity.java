package com.ict.ex65_db;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker datePicker;
    EditText edit;
    Button button;
    MyDatabase myDatabase = new MyDatabase(this);


    //Db에 들어갈 변수
    String dailyDate;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker = findViewById(R.id.datePicker);
        edit = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        int cYear = Calendar.getInstance().get(Calendar.YEAR);
        int cMonth = Calendar.getInstance().get(Calendar.MONTH);
        int cDay = Calendar.getInstance().get((Calendar.DAY_OF_MONTH));
        dailyDate = cYear + "_" + (cMonth + 1) + "_" +cDay;

        content = readDB();
        edit.setText(content);

        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int y, int m, int d) {
                dailyDate = y+"_"+(m+1)+"_"+d;
                String msg = readDB();
                edit.setText(msg);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(button.getText().toString().equals("스케줄 저장")){
                    SQLiteDatabase db = myDatabase.getWritableDatabase();
                    String sql = "insert into daily(dailyDate, content) values(?, ?)";
                    String[] arr = {dailyDate, edit.getText().toString()};
                    db.execSQL(sql, arr);
                    myDatabase.close();
                    button.setText("스케줄 수정");

                }else if(button.getText().toString().equals("스케줄 수정")){
                    SQLiteDatabase db = myDatabase.getWritableDatabase();
                    String sql = "update daily set content=? where dailyDate=?";
                    String[] arr = {edit.getText().toString(), dailyDate};
                    db.execSQL(sql, arr);
                    myDatabase.close();

                }
            }
        });
    }

    public String readDB(){
        String msg="";
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        String sql = "select * from daily where dailyDate = ?";
        String[] arr = {dailyDate};
        Cursor cursor = db.rawQuery(sql, arr);
        if(cursor.getCount() == 0){
            edit.setHint("스케줄 없음.");
            button.setText("스케줄 저장");
        }else{
            while(cursor.moveToNext()){
                msg = cursor.getString(1);
                button.setText("스케줄 수정");
            }
            //받은 스케줄 edit text에 표시
            edit.setTextColor(Color.BLUE);

        }
        cursor.close();
        myDatabase.close();
        return msg;
    }

}