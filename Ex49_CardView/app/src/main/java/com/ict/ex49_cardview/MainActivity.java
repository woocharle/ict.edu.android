package com.ict.ex49_cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter();

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);

        adapter.addItem(new VO("코드1", "547700",
                "베스트 상품", R.drawable.clothes1));
        adapter.addItem(new VO("코드2", "577700",
                "이달의 상품", R.drawable.clothes2));
        adapter.addItem(new VO("코드3", "677700",
                "이달의 상품", R.drawable.clothes3));
        adapter.addItem(new VO("코드4", "487700",
                "이달의 상품", R.drawable.clothes4));
        adapter.addItem(new VO("코드5", "584400",
                "이달의 상품", R.drawable.clothes5));

        recyclerView.setAdapter(adapter);

        // 이벤트
        adapter.setOnItemClickListener(new MyListener() {
            @Override
            public void onItemClick(MyAdapter.ViewHolder holder, View view, int position) {
                VO vo = adapter.getItem(position);
               // Toast.makeText(MainActivity.this, vo.getName()+"선택됨", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("title", vo.getName());
                intent.putExtra("img", vo.getResId());
                intent.putExtra("content_1", vo.getEvent());
                intent.putExtra("content_2", vo.getPrice());
                startActivity(intent);
            }
        });
    }
}