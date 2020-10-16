package com.ict.ex82_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity3 extends AppCompatActivity {
    EditText editText2;
    Button button2;
    TextView result2;
    Socket s;
    Handler handler = new Handler();
    String msg;
    static final String IP = "203.236.220.55";
    static final int PORT = 8889;
    BufferedWriter writer ;
    BufferedReader reader ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        editText2 = findViewById(R.id.editText2);
        button2 = findViewById(R.id.button2);
        result2 = findViewById(R.id.result2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                connet();
            }
        }).start();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sendMsg(IP+":"+editText2.getText().toString());
                        }
                    }).start();
                    editText2.setText("");
                }catch (Exception e){
                }
            }
        });
    }
    private void connet(){
        try {
            s = new Socket(IP, PORT);
            if(s != null){
                writer =
                        new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                 reader =
                        new BufferedReader(new InputStreamReader(s.getInputStream()));
                // 접속이 끊어지면 false
                while (s.isConnected()){
                    msg = reader.readLine();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                           // result2.setText(result2.getText().toString()+"\n"+msg);
                            result2.append(msg+"\n");
                        }
                    });
                }
            }
        }catch (Exception e){
        }
    }
    private void sendMsg(String msg){
        try {
            writer.write(msg+System.getProperty("line.separator"));
            writer.flush();
        }catch (Exception e){
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            s.close();
        }catch (Exception e){
        }
    }
}