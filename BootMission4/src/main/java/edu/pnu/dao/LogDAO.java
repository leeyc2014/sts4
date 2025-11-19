package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import edu.pnu.dao.MemberDAO;

public class LogDAO {
	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	MemberDAO member;
	
	public LogDAO() {
		this.member = new MemberDAO();
		try	{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/bootmission";
			String id = "musthave";
			String pwd = "tiger";
			con = DriverManager.getConnection(url, id, pwd);

			System.out.println("DB 연결 성공(기본 생성자)");
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
	}
	
	public void addLog(Map<String, Object> map) {	
		String query = "INSERT INTO dblog (method, sqlstring, regidate, success) VALUES (?, ?, now(), ?)";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, (String) map.get("method"));
			psmt.setString(2, (String) map.get("sqlstring"));
			psmt.setBoolean(3, (boolean) map.get("success"));
			psmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
