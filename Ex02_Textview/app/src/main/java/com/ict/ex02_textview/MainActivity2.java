package com.ict.ex02_textview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    TextView txt1, txt2, txt3, txt4, txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_02);

        //findviewById()로 layout에 있는 view로 인식하기.
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);

        txt1.setOnClickListener(this);

        //방법2: 내부클래스를 사용한다.
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2.setText("대한민국 서울");
            }
        });

        //방법3: OnclickListner가 있는 내부클래스를 별도로 만들어서 사용한다.
        txt3.setOnClickListener(new InnerEvent());

    }

    /* alt + Insert = 단축키*/
    public void onClick(View view){
        //Toast.makeText(this, "글자를 눌렀습니다.", Toast.LENGTH_SHORT).show();
        txt2.setVisibility(View.VISIBLE);
        txt4.setVisibility(View.VISIBLE);
    }

    class InnerEvent implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            txt4.setTextColor(Color.GREEN);
        }
    }


}