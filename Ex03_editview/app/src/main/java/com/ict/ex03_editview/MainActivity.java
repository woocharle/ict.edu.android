package com.ict.ex03_editview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit1, edit2, edit3;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        textView1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = edit1.getText().toString();
        int age = Integer.parseInt(edit2.getText().toString());
        String password = edit3.getText().toString();

        textView2.setText("이름:" + name + "\n" + "나이:" + age + "\n"+"비번:" + password);
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
    }

}