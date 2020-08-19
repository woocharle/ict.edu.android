package com.ict.ex12_seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView tv2;
    SeekBar sb2 ;
    // 움직이는 값을 기억하는 변수
    int position = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv2 = findViewById(R.id.tv2);
        sb2 = findViewById(R.id.sb2);

        // seekBar 이벤트
         sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             // 변경될때
             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 position = sb2.getProgress();
                 tv2.setText("밝기 : "+ position + "%");

                 // 실제 폰의 화면밝기 조절 메소드 호출
                 setBrigthness(position);
             }
            // 움직임이 시작될때
             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {
                  //  position = sb2.getProgress();
             }
             // 움직임이 끝날 때
             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {
                //  position = sb2.getProgress();
             }
         });
    }

    public void setBrigthness(int k){
       // 실제 폰 밝기 조절
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float)  k / 100.0f ;
        getWindow().setAttributes(params);
    }
}