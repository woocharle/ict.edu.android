package com.ict.network03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Network03 implements Runnable{
	ServerSocket ss;
	Socket s;
	BufferedReader reader;
	BufferedWriter writer;
	
	public Network03() {
		try {
			ss = new ServerSocket(7889);
			System.out.println("서버 실행 중 ....");
			
			new Thread(this).start();
		} catch (Exception e) {
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				s = ss.accept();
				reader = 
					new BufferedReader(new InputStreamReader(s.getInputStream()));
				writer = 
					new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				String msg = reader.readLine();
				if(msg.equals("test")) {
					// ���� �˻�
					writer.write("connet success" + System.getProperty("line.separator"));
					writer.flush();
				}else if(msg.equals("db")) {
					DBConnection db = new DBConnection();
					ArrayList<String> list = db.selectAll();
					if(list.size() > 0) {
						StringBuffer sb = new StringBuffer();
						for (String k : list) {
							sb.append(k+",");
						}
						writer.write(sb.toString()+System.getProperty("line.separator"));
						writer.flush();
					}else {
						writer.write("no data"+System.getProperty("line.separator"));
						writer.flush();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}	
	}
	public static void main(String[] args) {
		new Network03();
	}	
	

}
