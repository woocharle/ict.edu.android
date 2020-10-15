package com.ict.network02;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiChat implements Runnable {
	private ArrayList<ChatThread> list;
	private Socket s;
	ServerSocket ss;
	ChatThread ct;
	
	public MultiChat() {
		list= new ArrayList<ChatThread>();
		try {
			ss = new ServerSocket(8889);
			System.out.println("���� ���� ��...");
			new Thread(this).start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				s = ss.accept();
				ct = new ChatThread(s, this);
				list.add(ct);
				ct.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	// ��� �ο����� �޼��� ����
	public void sendMsg(String msg) {
		try {
			msg += System.getProperty("line.separator");
			System.out.println(msg);
			for (ChatThread k : list) {
				k.writer.write(msg);
				k.writer.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//�����ο� ���� 
	public void delChatThread(ChatThread ct) {
		list.remove(ct);
		sendMsg(ct.bye[0] + "�� ���� �߽��ϴ�.");
	}
	
	public static void main(String[] args) {
		new MultiChat();
	}
	
}
