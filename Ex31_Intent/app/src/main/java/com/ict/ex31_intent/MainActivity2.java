package com.ict.ex31_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText editText1;
    Button button2, button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText1 = findViewById(R.id.editText1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        final Intent intent = getIntent();
        String addr = intent.getStringExtra("addr");
        editText1.setText(addr);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.putExtra("addr_e", editText1.getText().toString());
                setResult(RESULT_OK, sendIntent);
                finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 되돌아갈때 인텐트 사용 하지 않을 수 있음
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}