package com.ict.ex82_socket_ref;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity5 extends AppCompatActivity {
    TextView result3 ;
    String msg;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        result3 = findViewById(R.id.result3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 앞에서는 java(Socket),  이제 web 이다.
                // msg = sendServer("http://www.ictedu.co.kr");
                msg = sendServer("http://203.236.220.55:8090/androidserver/index.jsp");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        result3.setText(msg);
                    }
                });
            }
        }).start();
    }
    private String sendServer(String addr){
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(addr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // 웹 페이지 연결에 성공하면
            if(conn != null){
                // 타입아웃 설정
                conn.setConnectTimeout(10000);
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br =
                            new BufferedReader(
                                    new InputStreamReader(
                                            conn.getInputStream(),"utf-8"));
                    while (true){
                        String line = br.readLine();
                        if(line == null) break;
                        sb.append(line+"\n");
                    }
                    br.close();
                }
                conn.disconnect();
            }
        }catch (Exception e){
        }
        return sb.toString();
    }
}