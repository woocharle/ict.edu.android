package com.ict.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.network03.DBConnection;
import com.ict.network03.VO;

@WebServlet("/MyController4")
public class MyController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		DBConnection db = new DBConnection();
		ArrayList<VO> list = db.selectAll3();
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(VO k : list) {
			sb.append("{");
			sb.append("\"idx\"" + ":\""+k.getIdx()+"\", ");
			sb.append("\"m_id\"" + ":\""+k.getM_id()+"\", ");
			sb.append("\"m_pw\"" + ":\""+k.getM_pw()+"\", ");
			sb.append("\"m_name\"" + ":\""+k.getM_name()+"\", ");
			sb.append("\"m_age\"" + ":\""+k.getM_age()+"\", ");
			sb.append("\"m_reg\"" + ":\""+k.getM_reg()+"\"");
			sb.append("},");
		}
		String msg = 
		sb.toString().substring(0,sb.toString().length()-1)+"]";
		out.println(msg);
		
	}
}

