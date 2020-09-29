package com.ict.ex33_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView[] iv = new ImageView[9];
    Integer[] img = { R.id.imageView1, R.id.imageView2, R.id.imageView3,
            R.id.imageView4, R.id.imageView5, R.id.imageView6,
            R.id.imageView7, R.id.imageView8, R.id.imageView9 };
    int[] count = new int[9]; // 점수 저장
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);

        for(int i=0; i < img.length; i++){
            iv[i] = findViewById(img[i]);
            count[i] = 0 ;

            // 이미지를 눌렀을 때 count 저장
            final int index = i ;
            iv[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count[index]++ ;
                }
            });
        }
        // 버튼을 누르면  화면전환하면서 count값을 넘겨 줘야 한다.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("count", count);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
           if(resultCode==RESULT_OK){
               String msg = data.getStringExtra("msg");
               Toast.makeText(this, msg +"에서 되돌아 왔네요", Toast.LENGTH_SHORT).show();
               // count를 초기화 한다.(이전정보를 삭제를 위해서)
               for(int i=0; i<count.length; i++){
                   count[i] = 0 ;
               }
           }
        }
    }
}