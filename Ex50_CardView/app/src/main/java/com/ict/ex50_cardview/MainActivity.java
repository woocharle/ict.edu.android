package com.ict.ex50_cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    TextView count;
    EditText txt1, txt2, txt3 ;
    Button btn1;
    RecyclerView  recyclerView ;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = findViewById(R.id.count);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);

        btn1 = findViewById(R.id.btn1);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter();

        LinearLayoutManager manager =
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        adapter.addItem(new VO("홍길동", "1988-08-08","010-8888-8888"));
        adapter.addItem(new VO("일지매", "1977-07-07","010-7777-7777"));
        adapter.addItem(new VO("임꺽정", "1999-09-09","010-9999-9999"));

        recyclerView.setAdapter(adapter);

        count.setText(adapter.getItemCount()+" 명");

        // 권한 자동 부여
        AutoPermissions.Companion.loadAllPermissions(this,100);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txt1.getText().toString();
                String birth = txt2.getText().toString();
                String phone = txt3.getText().toString();

                adapter.addItem(new VO(name,birth,phone));
                count.setText(adapter.getItemCount()+" 명");

                // 반응이 한템포 느린 부분 처리
                adapter.notifyDataSetChanged();

                // 입력내용 지우기
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");

            }
        });

        adapter.setOnItemClickListener(new MyListener() {
            @Override
            public void onItemClick(MyAdapter.ViewHolder holder, View view, int position) {
                // 전화 걸기 (사용허가 받기)
                // 1. Manifest.xml 에서 전화 사용 허가 받기
                // <uses-permission android:name="android.permission.CALL_PHONE" />
                // 2. 빌더 (spring-maven (pox.xml), android-gradle)
                // 3. MainActivity 에서 권한 자동 부여하기
                // 3-1 .  implements AutoPermissionsListener 추가
                // 3-2. 오버라이딩 해야 한다.
                //    public void onDenied(int i, String[] strings) { }
                //    public void onGranted(int i, String[] strings) { }
                // 3-3.
                //   AutoPermissions.Companion.loadAllPermissions(this,100);
                VO vo = adapter.getItem(position);
                // Intent intent = new Intent();  // 통화버튼
                Intent intent = new Intent(Intent.ACTION_CALL);  // 바로전화
                intent.setData(Uri.parse("tel:"+vo.getPhone()));
                if(intent.resolveActivity(getPackageManager()) != null){
                    if(checkSelfPermission(Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDenied(int i, String[] strings) { }
    @Override
    public void onGranted(int i, String[] strings) {   }
}