package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.MemberDTO;

public class MemberDAO {
	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	
	public MemberDAO() {
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

	public List<MemberDTO> getAllMember() {

		List<MemberDTO> list = new ArrayList<>();
		String query = "SELECT * FROM member"; 

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
				list.add(dto);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public MemberDTO getMemberById(@PathVariable Integer id) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id = ?"; 

		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));

			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
		String query = "INSERT INTO member(pass, name, regidate) VALUES (?, ?, now())"; 

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());			
			psmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return memberDTO;
	}

	public MemberDTO putMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		String query = "UPDATE member SET pass = ?, name = ?, regidate = now() WHERE id = ?"; 

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			psmt.setInt(3, id);
			psmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return memberDTO;
	}

	public MemberDTO patchMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		StringBuilder query = new StringBuilder("UPDATE member SET "); 

		try {	
			if(memberDTO.getPass() != null) {
				query.append("pass = ?");
			}
			else if(memberDTO.getName() != null) {
				query.append("name = ?");
			}
			
			query.append(", regidate = now() WHERE id = ?");
			psmt = con.prepareStatement(query.toString());
			
			if(memberDTO.getPass() != null) {
				psmt.setString(1, memberDTO.getPass());
				psmt.setInt(2, id);
			}
			else if(memberDTO.getName() != null) {
				psmt.setString(1, memberDTO.getName());
				psmt.setInt(2, id);
			}
			psmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return memberDTO;
	}

	public void deleteMember(@PathVariable Integer id) {
		String query = "DELETE FROM member WHERE id = ?"; 

		try {
			// 쿼리 실행
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();		
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
