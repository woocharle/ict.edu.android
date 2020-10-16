package com.ict.ex82_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity10 extends AppCompatActivity {
    Button btn5 ;
    TextView result5;
    Handler handler = new Handler();
    String msg ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        btn5 = findViewById(R.id.btn5);
        result5 = findViewById(R.id.result5);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverConnect("http://203.236.220.55:8090/androidserver/MyController4");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                jsonProcess();
                            }
                        });
                    }
                }).start();
            }
        });
    }
    private void serverConnect(String str){
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader( new InputStreamReader(
                            conn.getInputStream(),"utf-8"));
                    while(true){
                        String line = br.readLine();
                        if(line == null) break;
                        sb.append(line+"\n");
                    }
                    br.close();
                };
                conn.disconnect();
            }
        }catch (Exception e){
        }
        msg = sb.toString();
    }
    private void jsonProcess(){
        try {
            BufferedReader br = new BufferedReader(new StringReader(msg));
            StringBuffer sb = new StringBuffer("JSON 파싱\n");
            String str = br.readLine();

            JSONArray array = new JSONArray(str);
            for (int i=0; i <array.length(); i++){
                sb.append(array.getJSONObject(i).getString("idx")+", ");
                sb.append(array.getJSONObject(i).getString("m_id")+", ");
                sb.append(array.getJSONObject(i).getString("m_pw")+", ");
                sb.append(array.getJSONObject(i).getString("m_name")+", ");
                sb.append(array.getJSONObject(i).getString("m_age")+", ");
                sb.append(array.getJSONObject(i).getString("m_reg"));
                sb.append("\n");
            }
            result5.setText(sb.toString());
        }catch (Exception e){
        }
    }
}