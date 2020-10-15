package com.ict.ex81_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2;
    Button button1, button2;
    TextView textView1, textView2;

    // 안드로이드는 네트워크에서 반드시 스레드 처리 해야 한다.
    // 핸들러 사용하자.

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
       // editText2 = findViewById(R.id.editText2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String data = editText1.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                        editText1.setText("");
                    }
                }).start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });

    }

    public void send(String data){
        try {
            int port = 8888;
            Socket socket = new Socket("localhost", port);
            printClientLog("소켓 연결함");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(data);
            outputStream.flush();
            printClientLog("데이터 전송함");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            printClientLog("서버로부터 받음: " + inputStream.readObject());
            socket.close();

        }catch (Exception e){
            Log.i("my",e+"");
        }
    }

    public void printClientLog(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView1.append(msg + "\n");
            }
        });
    }

    //클라이언트 연결, 데이터 받기, 데이터 보기
    public void startServer(){
        try {
            int port = 8888;
            ServerSocket ss = new ServerSocket(port);
            printClientLog("소켓 연결함" + port);
            while(true){
                Socket s = ss.accept();
                InetAddress clientHost = s.getLocalAddress();
                int clientPort = s.getPort();
                printServerLog("클라이언트 연결됨 : " + clientHost + ":" + clientPort);

                ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
                Object obj = inputStream.readObject();
                printServerLog("데이터 받음: " + obj);

                ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
                outputStream.writeObject(obj);
                outputStream.flush();
                printServerLog("데이터 보냄 ");
                //ss.close();  서버시작버튼을 매번 눌러야 한다.
                s.close();

            }
        }catch (Exception e){

        }
    }

    public void printServerLog(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView2.append(msg + "\n");
            }
        });
    }

}