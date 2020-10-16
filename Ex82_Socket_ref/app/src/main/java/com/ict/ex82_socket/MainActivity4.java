package com.ict.ex82_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity4 extends AppCompatActivity {
    Button btn1, btn2;
    ListView list;
    Socket s;
    Handler handler = new Handler();
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        list = findViewById(R.id.list);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        msg = sendServer("203.236.220.55", 7889, "test");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity4.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        msg = sendServer("203.236.220.55", 7889, "db");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                              //  list에 배열로 답아서 내본다.
                                String[] data = null;
                                try{
                                    data = msg.split(",");
                                    ArrayAdapter<String> adapter =
                                            new ArrayAdapter<String>(MainActivity4.this,
                                                    android.R.layout.simple_list_item_1, data);
                                    list.setAdapter(adapter);
                                }catch (Exception e){
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
    private String sendServer(String ip, int port, String msg){
       String str = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            s = new Socket(ip,port);
            writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

            msg = msg + System.getProperty("line.separator");
            writer.write(msg);
            writer.flush();

            str = reader.readLine();
            s.close();
        }catch (Exception e){
        }
       return str;
    }
}