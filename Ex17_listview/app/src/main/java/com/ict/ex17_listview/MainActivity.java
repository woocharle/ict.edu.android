package com.ict.ex17_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     ListView listView;
     UserAdapter adapter;
     ArrayList<VO> list;
     ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        listView = findViewById(R.id.listView);
        img = findViewById(R.id.image1);

        list = new ArrayList<VO>();
        list.add(new VO(R.drawable.pic1, "아기사진1" ));
        list.add(new VO(R.drawable.pic2, "아기사진2" ));
        list.add(new VO(R.drawable.pic3, "아기사진3" ));
        list.add(new VO(R.drawable.pic4, "아기사진4" ));
        list.add(new VO(R.drawable.pic5, "아기사진5" ));
        list.add(new VO(R.drawable.pic6, "아기사진6" ));

        adapter = new UserAdapter(this, R.layout.user_item, list);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*일반적인 어댑터 처럼 처리하면 오류 발생*/

                String msg = (String) listView.getAdapter().getItem(i);
                Toast.makeText(MainActivity.this, msg+"선택", Toast.LENGTH_SHORT).show();
            }
        });

    }
}