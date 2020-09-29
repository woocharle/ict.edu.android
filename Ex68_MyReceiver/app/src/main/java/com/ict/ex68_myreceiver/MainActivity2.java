package com.ict.ex68_myreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textView1, textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        // 리시버에서 호출한 액티비티가 메모리에 존재하지 않았던 액티비티이므로
        // onCreate()에서 실행
        Intent intent = getIntent();
        processIntent(intent);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }

    private void processIntent(Intent intent){
        String sender = intent.getStringExtra("sender");
        String contents = intent.getStringExtra("contents");
        textView1.append(sender);
        textView2.append(contents);
    }
}