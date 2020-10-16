package com.ict.ex82_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity8 extends AppCompatActivity {
    WebView webView3 ;
    Handler handler = new Handler();
    String msg ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        webView3 = findViewById(R.id.webView3);

        // 서버의 서블릿으로 간다.
        new Thread(new Runnable() {
            @Override
            public void run() {
                serverConnect("http://203.236.220.55:8090/androidserver/MyController");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        process01();
                    }
                });
            }
        }).start();

    }

    private void serverConnect(String str){
    StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream(),"utf-8"));
                    while(true){
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
        msg = sb.toString();
    }

    private void process01(){
        // 확인
        // webView3.loadData(msg,"text/html", "utf-8");
        StringBuffer sb = new StringBuffer();
        sb.append("<style type=\"text/css\">" +
                "table { width: 100%; color : blue}" +
                "table, th, td {border: 1px solid red; text-align: center;}" +
                "</style>");
        sb.append("<h2>Member 전체 정보</h2>");
        sb.append("<table><thead>");
        sb.append("<tr><th>번호</th><th>아이디</th><th>패스워드</th><th>이름</th>" +
                "<th>나이</th><th>날짜</th></tr></thead>");
        String[] data = msg.split("/");
        sb.append("<tbody>");
        for (String k : data) {
            sb.append("<tr>");
            String[]cols = k.split(",");
            for(String k2 : cols){
                sb.append("<td>"+k2+"</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</tbody></table>");
        webView3.loadData(sb.toString(),"text/html", "utf-8");
    }
}