package com.ict.ex14_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner1, spinner2, spinner3;
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        imageView1 = findViewById(R.id.imageView1);

        // 1. 스피너에 들어갈 데이터는 String[]())이다.
        String[] arr1 = {"과일의종류", "맘고", "두리안", "용과", "람부탁", "메론"};

        // 2. 어댑터를 사용 : 스피너 , 리스트
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(
                      // 컨텍스트, 모양, 내용에 들어가 배열
                      this, android.R.layout.simple_spinner_dropdown_item,  arr1 );

        // 3. 스피너에게 어댑터를 장착
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // int i => 선택된 객체위치, long l = > 선택된 객체의 ID
                String msg = (String) spinner1.getAdapter().getItem(i);
                Toast.makeText(MainActivity.this, msg+" 선택", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    // 스피너 2는 개발자가 미리 등록해서 사용하는 것
    // values-spinner2(이름)
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // int i => 선택된 객체위치, long l = > 선택된 객체의 ID
                String msg = (String) spinner2.getAdapter().getItem(i);
                Toast.makeText(MainActivity.this, msg+" 선택", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {        }
        });
        String[] arr3 = {"그림선택","boy","coffee", "dog","donald"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,arr3 );
        spinner3.setAdapter(adapter1);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1 : imageView1.setImageResource(R.drawable.boy); break;
                    case 2 : imageView1.setImageResource(R.drawable.coffe); break;
                    case 3 : imageView1.setImageResource(R.drawable.dog); break;
                    case 4 : imageView1.setImageResource(R.drawable.donald);break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }
}