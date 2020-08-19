package com.ict.ex08_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        /* editText 이벤트 */
        editText.addTextChangedListener(new TextWatcher() {
            /* 변경 전 */
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /* s => 문자열 */
                /*
                   byte[] b = s.toString().getBytes();
                   int sCount = b.length ;
                   textView.setText(sCount + " / 80 바이트");
                 */
                char[] b = s.toString().toCharArray();
                int sCount = b.length;
                textView.setText(sCount + " /  문자");
            }
            /* 변경 후 */
            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }
}