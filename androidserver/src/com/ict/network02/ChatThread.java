package com.ict.network02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ChatThread extends Thread{
	Socket s;
	MultiChat mt;
	BufferedReader reader;
	BufferedWriter writer;
	String[] bye;
	
	public ChatThread() {}

	public ChatThread(Socket s, MultiChat mt) {
		try {
			this.s = s;
			this.mt = mt;
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	@Override
	public void run() {
		try {
			while(true) {
				String msg = reader.readLine();
				bye = msg.split(":");
				if (! bye[1].equalsIgnoreCase("exit")) {
					mt.sendMsg(msg);
				}else {
					String str = "bye~~~~~~";
					str += System.getProperty("line.separator");
					writer.write(str);
					writer.flush();
					mt.delChatThread(this);
					break;
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
