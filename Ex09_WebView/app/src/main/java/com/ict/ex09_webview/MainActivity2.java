package com.ict.ex09_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText edit;
    Button btn;
    WebView webView2;
    WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edit = findViewById(R.id.edit);
        btn = findViewById(R.id.btn);
        webView2 = findViewById(R.id.webView2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 웹 뷰에 브라우저 세팅
                webView2.setWebChromeClient(new WebChromeClient());

                webSettings = webView2.getSettings();
                webSettings.setJavaScriptEnabled(true);
                String url = edit.getText().toString();
                webView2.loadUrl(url);
            }
        });
    }
}