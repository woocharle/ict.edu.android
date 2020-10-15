package com.ict.network03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	
	public DBConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##jwc";
			String password ="1112";  
			conn = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
		}
	}
	
	// members의 전체 정보 보기 
	public ArrayList<String> selectAll(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			String sql = "select * from members order by idx";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				StringBuffer sb = new StringBuffer();
				sb.append(rs.getString("idx")+"\t");
				sb.append(rs.getString("m_id")+"\t");
				sb.append(rs.getString("m_pw")+"\t");
				sb.append(rs.getString("m_name")+"\t");
				sb.append(rs.getString("m_age")+"\t");
				sb.append(rs.getString("m_reg").substring(0, 10)+"\t");
				list.add(sb.toString());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	// MyController에서 사용 => MainActivity7
	public String selectAll2(){
		StringBuffer sb = new StringBuffer();
		try {
			String sql = "select * from members order by idx";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				sb.append(rs.getString("m_id")+",");
				sb.append(rs.getString("m_pw")+",");
				sb.append(rs.getString("m_name")+",");
				sb.append(rs.getString("m_age")+",");
				sb.append(rs.getString("m_reg").substring(0, 10)+"/");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sb.toString();
	}
	
}
