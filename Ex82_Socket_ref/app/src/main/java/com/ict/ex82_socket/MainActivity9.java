package com.ict.ex82_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity9 extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;
    TextView result4;
    Handler handler = new Handler();
    String msg ,sax_msg1, sax_msg2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        result4 = findViewById(R.id.result4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverConnect("http://203.236.220.55:8090/androidserver/MyController2");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                xmlProcess01();
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
                        serverConnect("http://203.236.220.55:8090/androidserver/MyController3");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                xmlProcess02();
                            }
                        });
                    }
                }).start();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverConnect("http://203.236.220.55:8090/androidserver/MyController2");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                xmlProcess03();
                                //result4.setText(sax_msg1);
                            }
                        });
                    }
                }).start();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverConnect("http://203.236.220.55:8090/androidserver/MyController3");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                xmlProcess04();
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
    // DOM 방식이고, XML이 태그로만 이루어져있음
    private void xmlProcess01(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(msg)));
            // 태그별로 정보 저장하기
            NodeList member = document.getElementsByTagName("member");
            NodeList idx = document.getElementsByTagName("idx");
            NodeList m_id = document.getElementsByTagName("m_id");
            NodeList m_pw = document.getElementsByTagName("m_pw");
            NodeList m_name = document.getElementsByTagName("m_name");
            NodeList m_age = document.getElementsByTagName("m_age");
            NodeList m_reg = document.getElementsByTagName("m_reg");

            StringBuffer sb = new StringBuffer("XML_Tag(DOM) 방식");
            for (int i=0; i < member.getLength(); i++){
                sb.append("\n");
                sb.append(
                        idx.item(i).getFirstChild().getNodeValue()+", " +
                        m_id.item(i).getFirstChild().getNodeValue()+", " +
                        m_pw.item(i).getFirstChild().getNodeValue()+", " +
                        m_name.item(i).getFirstChild().getNodeValue()+", " +
                        m_age.item(i).getFirstChild().getNodeValue()+", " +
                        m_reg.item(i).getFirstChild().getNodeValue()
                );
            }
            result4.setText(sb.toString());
        }catch (Exception e){
        }
    }
    // DOM 방식이고, XML이 태그와 속성으로만 이루어져있음
    private void  xmlProcess02(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(msg)));

            // 태그 구하기
            NodeList member = document.getElementsByTagName("member");
            StringBuffer sb = new StringBuffer("XML_Attr(DOM) 방식");
            for (int i=0; i<member.getLength(); i++){
                sb.append("\n");
                sb.append(
                        member.item(i).getFirstChild().getNodeValue()+", "+
                        ((Element)member.item(i)).getAttribute("idx")+", " +
                        ((Element)member.item(i)).getAttribute("m_id")+", " +
                        ((Element)member.item(i)).getAttribute("m_pw")+", " +
                        ((Element)member.item(i)).getAttribute("m_age")+", " +
                        ((Element)member.item(i)).getAttribute("m_reg"));
            }
            result4.setText(sb.toString());
        }catch (Exception e){
        }
    }
    // SAX방식이고, XML이 태그로만 이루어져있음
    private void xmlProcess03()  {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            // SAX를 파싱하는 내부클래스 별도 생성
            reader.setContentHandler(new SAX_process01());
            reader.parse(new InputSource(new StringReader(msg)));
        }catch (Exception e){
        }
    }
    // SAX방식이고, XML이 태그와 속성으로만 이루어져있음
    private void xmlProcess04(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            // SAX를 파싱하는 내부클래스 별도 생성
            reader.setContentHandler(new SAX_process02());
            reader.parse(new InputSource(new StringReader(msg)));
        }catch (Exception e){
        }
    }
    // 태그만 있는 XML는 시작태그, 끝태그사이의 문자만 추출
    class SAX_process01 extends DefaultHandler{
            StringBuffer sb = new StringBuffer("XML_Tag(SAX) 방식\n ");
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            sb.append(new String(ch,start,length).trim()+" ");
        }
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
           if(qName.equals("m_reg")){
               sb.append("\n");
           }
           result4.setText(sb.toString());
        }
    }
    // 속성과 태그로 이루어진 XML
    class SAX_process02 extends DefaultHandler{
        StringBuffer sb = new StringBuffer("XML_Attr(SAX) 방식\n");
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
           if(qName.equals("member")){
               sb.append(attributes.getValue("idx")+" ");
               sb.append(attributes.getValue("m_id")+" ");
               sb.append(attributes.getValue("m_pw")+" ");
               sb.append(attributes.getValue("m_age")+" ");
               sb.append(attributes.getValue("m_reg")+" ");
           }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            sb.append(new String(ch,start,length).trim());
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            sb.append("\n");
            result4.setText(sb.toString());
        }
    }
}