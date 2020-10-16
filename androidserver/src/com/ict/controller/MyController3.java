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

@WebServlet("/MyController3")
public class MyController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		DBConnection db = new DBConnection();
		ArrayList<VO> list = db.selectAll3();
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); 
		out.print("<members>");
		for (VO k : list) {
			out.println("<member idx= \""+k.getIdx()+"\"" 
					  + " m_id= \""+k.getM_id()+"\""
					  + " m_pw= \""+k.getM_pw()+"\""
					  + " m_age= \""+k.getM_age()+"\""
					  + " m_reg= \""+k.getM_reg()+"\">" 
					  + k.getM_name() +  "</member>");			
		}
		out.print("</members>");
	}
}
