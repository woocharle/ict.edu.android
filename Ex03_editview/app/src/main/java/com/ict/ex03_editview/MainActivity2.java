package com.ict.ex03_editview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    EditText a, b, edit3;
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        edit3 = findViewById(R.id.edit3);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);

        text1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            int su1 = Integer.parseInt(a.getText().toString());
            int su2 = Integer.parseInt(b.getText().toString());
            double res = 0;
            String ans = "";
            String op = edit3.getText().toString();
            boolean chk = true;

            switch (op){
                case "+": res = su1 + su2; break;
                case "-": res = su1 - su2; break;
                case "x": res = su1 * su2; break;
                case "/": res = (int)(su1 / (double)su2 * 100) / (double)100.0 ; break;
                default:
                    ans = "연산자를 다시 입력하시오.";
                    chk = false;
                    break;
            }

            if (chk == true){
                text2.setText("계산결과: " + res);
                a.setText("");
                b.setText("");
                edit3.setText("");
            } else{
                text2.setText(ans);
                a.setText("");
                b.setText("");
                edit3.setText("");
            }
        } catch (Exception e){
            Toast.makeText(this, "제대로 입력하시오.", Toast.LENGTH_SHORT).show();
        }







    }
}