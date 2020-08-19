package com.ict.ex09_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity3 extends AppCompatActivity {
    WebView webView3;
    WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        webView3 = findViewById(R.id.webView3);
        webView3.setWebChromeClient(new WebChromeClient());
        webSettings = webView3.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // webView3.loadUrl("인터넷 주소");

        String chtml = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>CCS 구문</title>" +
                "<style type=\"text/css\">" +
                "/* 태그선택자 = 요소 선택자 */" +
                "p{" +
                "color: tomato;" +
                "text-align: center;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                " <p> Hello World ! </p>" +
                " <p> ICT 인재 개발원 :: 자바 기반의 웹 앱 콘텐츠 개발자 과정 </p>" +
                "</body>" +
                "</html>";

        webView3.loadData(chtml, "text/html","utf-8");
    }
}