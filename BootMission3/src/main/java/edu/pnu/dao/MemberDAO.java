package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.MemberDTO;

public class MemberDAO {
	static Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;

	public MemberDTO getAllMember() {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member"; 

		try {
			// 쿼리 실행
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			// 결과 처리
			if (rs.next()) {
				dto.setId(rs.getInt(1));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public MemberDTO getMemberById(@PathVariable Integer id) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id = ?"; 

		try {
			// 쿼리 실행
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();

			// 결과 처리
			if (rs.next()) {
				dto.setId(rs.getInt(1));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
		MemberDTO dto = new MemberDTO();
		String query = "INSERT INTO member(pass, name, regidate) VALUES (?, ?, ?)"; 

		try {
			// 쿼리 실행
			psmt = con.prepareStatement(query);
			psmt.setInt(1, );
			rs = psmt.executeQuery();

			// 결과 처리
			if (rs.next()) {
				dto.setId(rs.getInt(1));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public MemberDTO putMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		return null;
	}

	public MemberDTO patchMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		return null;
	}

	public void deleteMember(@PathVariable Integer id) {
		
	}
	
	
	public static void main(String[] args) {
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
}
