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

@WebServlet("/MyController2")
public class MyController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 태그로만 이루어진 xml
		DBConnection db = new DBConnection();
		ArrayList<VO> list = db.selectAll3();
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.print("<members>");
		for(VO k : list) {
			out.println("<member>");
			out.println("<idx>"+k.getIdx()+"</idx>");
			out.println("<m_id>"+k.getM_id()+"</m_id>");
			out.println("<m_pw>"+k.getM_pw()+"</m_pw>");
			out.println("<m_name>"+k.getM_name()+"</m_name>");
			out.println("<m_age>"+k.getM_age()+"</m_age>");
			out.println("<m_reg>"+k.getM_reg()+"</m_reg>");
			out.println("</member>");
		}
		out.print("</members>");
	}
}











