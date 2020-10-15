package com.ict.ex82_socket2;

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
    EditText editText2_1;
    Button button2_1;
    TextView result2;
    Socket s;
    Handler handler = new Handler();
    String msg;

    static final String IP = "203.236.220.86";
    static final int PORT = 8889;
    BufferedWriter writer;
    BufferedReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText2_1 = findViewById(R.id.editText2_1);
        button2_1 = findViewById(R.id.button2_1);
        result2 = findViewById(R.id.result2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                connet();
            }
        }).start();

        button2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String str = IP + ":" + editText2_1.getText().toString();
                            Log.i("my", str);
                            sendMsg(str);
                        }
                    }).start();
                    editText2_1.setText("");
                }catch (Exception e){
                }
            }
        });
    }

    private void connet(){
        try {
            s = new Socket(IP, PORT);
            if(s != null){
                writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                while(s.isConnected()){
                    Log.i("my", "1");
                    msg = reader.readLine();
                    Log.i("my", "2");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            result2.append(msg+"\n");
                        }
                    });
                }
            }
        }catch (Exception e){
            Log.i("my",e+"");
        }
    }

    private void sendMsg(String msg){
        try{
            writer.write(msg + System.getProperty("line.separator"));
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