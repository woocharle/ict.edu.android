package com.ict.ex82_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity6 extends AppCompatActivity {
    WebView webView1 ;
    WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        webView1 = findViewById(R.id.webView1);

        webView1.setWebChromeClient(new WebChromeClient());

        webSettings = webView1.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView1.loadUrl("http://203.236.220.55:8090/androidserver/index.jsp");
    }
}