package com.ict.ex82_socket2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity6 extends AppCompatActivity {
    WebView webView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        webView2 = findViewById(R.id.webView2);

        String cHtml = "<!DOCTYPE html>\n"+
                        "<html>\n"+
                        "<body>\n"+

                        "<h2>Basic HTML Table</h2>\n"+
                        "<table style='width:100%' border='1px'>\n"+
                        "<tr>\n"+
                            "<th>Firstname</th>\n"+
                            "<th>Lastname</th> \n"+
                            " <th>Age</th>\n"+
                        "</tr>\n"+
                        "<tr>\n"+
                            "<td>Jill</td>\n"+
                            "<td>Smith</td>\n"+
                            "<td>50</td>\n"+
                        "</tr>\n"+
                        "<tr>\n"+
                            "<td>Eve</td>\n"+
                            "<td>Jackson</td>\n"+
                            "<td>94</td>\n"+
                        "</tr>\n"+
                        "<tr>\n"+
                            "<td>John</td>\n"+
                            "<td>Doe</td>\n"+
                            "<td>80</td>\n"+
                        "</tr>\n"+
                        "</table>\n"+

                        "</body>\n"+
                        "</html>\n";

        webView2.loadData(cHtml, "text/html", "utf-8");

    }
}