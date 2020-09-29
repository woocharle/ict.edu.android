package com.ict.ex32_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button1 = findViewById(R.id.button1);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        if(intent != null){
            Toast.makeText(this, msg+"에서 옴", Toast.LENGTH_SHORT).show();
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editText1.getText().toString();
                String pw = editText2.getText().toString();
                if(id.length() == 0 || pw.length() == 0){
                    Toast.makeText(MainActivity.this, "아이디와 비밀번호를 입력하고 눌러주세요", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("id", id);
                    intent.putExtra("pw", pw);

                    startActivityForResult(intent, 100);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode==RESULT_OK){
                editText1.setText("");
                editText2.setText("");
                String msg = data.getStringExtra("msg");
                Toast.makeText(this, msg+"에서 옴", Toast.LENGTH_SHORT).show();
            }else if (resultCode==RESULT_CANCELED){

            }
        }
    }
}