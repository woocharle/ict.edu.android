package com.ict.ex66_db_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    RadioGroup rg;
    Button button;
    TextView count;
    ListView list;
    DAO dao;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        rg = findViewById(R.id.rg);
        button = findViewById(R.id.home3);
        count = findViewById(R.id.textView);
        list = findViewById(R.id.list);

        rg.check(R.id.desc);

        dao = DAO.db_open(this);
        cursor = dao.selectDesc();
        count.setText("전체건수" + cursor.getCount());

        disp();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.asc){
                    cursor = dao.selectAsc();
                    disp();
                }else if(i == R.id.desc){
                    cursor = dao.selectDesc();
                    disp();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void disp(){
        String[] arr = new String[cursor.getCount()];
        int count = 0;
        while(cursor.moveToNext()){
            String date = cursor.getString(1);
            String schedule = cursor.getString(2);
            arr[count++] = date+" ☞ " + schedule.substring(0,4)+"...";
        }

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] date = parent.getAdapter().getItem(position).toString().split(" ☞ ");
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                intent.putExtra("date", date[0].trim());
                startActivity(intent);
                finish();
            }
        });

    }

}