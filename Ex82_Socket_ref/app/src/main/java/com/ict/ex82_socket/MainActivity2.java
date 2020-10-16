package com.ict.ex82_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity2 extends AppCompatActivity {
    EditText editText1;
    Button button1;
    TextView result1;
    Handler handler = new Handler();
    String msg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText1 = findViewById(R.id.editText1);
        button1 = findViewById(R.id.button1);
        result1 = findViewById(R.id.result1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 네트워크는 무조건 스레드 처리 해야 된다.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String str = editText1.getText().toString();
                        msg = sendServer(str);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                result1.append(msg+"\n");
                                editText1.setText("");
                            }
                        });
                    }
                }).start();
            }
        });
    }
    // 서버와 통신하는 메소드
    public String sendServer(String str){
        String res = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            Socket s = new Socket("203.236.220.55",9999);
            writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

            writer.write(str+System.getProperty("line.separator"));
            writer.flush();

            res = reader.readLine();
            s.close();
        }catch (Exception e){
            Log.d("my",e+"");
        }
        return res;
    }
}