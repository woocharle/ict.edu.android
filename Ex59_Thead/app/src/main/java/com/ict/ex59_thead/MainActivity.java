package com.ict.ex59_thead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt1;
    ProgressBar progressBar1;
    Button button1;
    int value = 0 ;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txt1);
        progressBar1 = findViewById(R.id.progressBar1);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<=progressBar1.getMax(); i++){
                            value = i;
                            try {
                                Thread.sleep(300);
                            }catch (Exception e){
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar1.setProgress(value);
                                    txt1.setText(progressBar1.getProgress()+" %");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}