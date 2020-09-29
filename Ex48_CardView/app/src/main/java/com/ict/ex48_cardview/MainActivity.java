package com.ict.ex48_cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

/* 1. vo,  2. 어댑터 3. 리스너 4. MainActivity */
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        // recyclerView 화면을 만들어줘야 한다.
        // 한 줄에 하나씩 보이기
          LinearLayoutManager manager =
                 new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,false);

        // 한줄에 여러개
        // GridLayoutManager manager =
        //        new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(manager);
        adapter = new PersonAdapter();
        adapter.addItem(new VO("홍길동", "010-7777-9999"));
        adapter.addItem(new VO("김길동", "010-1111-9999"));
        adapter.addItem(new VO("이길동", "010-2222-9999"));
        adapter.addItem(new VO("박길동", "010-3333-9999"));
        adapter.addItem(new VO("고길동", "010-4444-9999"));

        recyclerView.setAdapter(adapter);

        // 이벤트 처리
        adapter.setOnItemClickListener(new OnPersonitemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                // 번호를 추출한다.
                VO vo = adapter.getItem(position);
                // 전화 걸기 : manifests에서 permission 해줘야 된다.
                // 앱 정보에서 허가를 또 받아야 한다.
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+vo.getPhone()));
                if(intent.resolveActivity(getPackageManager()) != null){
                    if(checkSelfPermission(Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                    startActivity(intent);
                }
            }
        });
    }
}






