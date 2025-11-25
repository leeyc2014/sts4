package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.MemberDTO;

@Repository
public class MemberDAO {
	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	boolean success = false;
	
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
			success = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("method", "GET");
		map.put("sqlstring", query);
		map.put("success", success);
		LogDAO logDAO = new LogDAO();
		logDAO.addLog(map);

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
			success = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		String sqlstring = "SELECT * FROM member WHERE id = " + id;
		map.put("method", "GET By Id");
		map.put("sqlstring", sqlstring);
		map.put("success", success);
		LogDAO logDAO = new LogDAO();
		logDAO.addLog(map);

		return dto;
	}

	public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
		String query = "INSERT INTO member(pass, name, regidate) VALUES (?, ?, now())"; 

		try {
			psmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			psmt.executeUpdate();
			
			rs = psmt.getGeneratedKeys();
			int id = 0;
	        if (rs.next()) {
	        	id = rs.getInt(1);
	            memberDTO.setId(id);
	        }
	        
	        String selectQuery = "SELECT * FROM member WHERE id = ?";
	        psmt = con.prepareStatement(selectQuery);
	        psmt.setInt(1, id);
	        rs = psmt.executeQuery();

	        if (rs.next()) {
	            memberDTO.setId(rs.getInt("id"));  
	            memberDTO.setPass(rs.getString("pass"));
	            memberDTO.setName(rs.getString("name"));
	            memberDTO.setRegidate(rs.getDate("regidate")); 
	        }
	        
			success = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		String sqlstring = "INSERT INTO member(pass, name, regidate) VALUES (" + memberDTO.getPass() + ", " + memberDTO.getName() +", now())";
		map.put("method", "POST");
		map.put("sqlstring", sqlstring);
		map.put("success", success);
		LogDAO logDAO = new LogDAO();
		logDAO.addLog(map);

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
			
			String selectQuery = "SELECT * FROM member WHERE id = ?";
	        psmt = con.prepareStatement(selectQuery);
	        psmt.setInt(1, id);
	        rs = psmt.executeQuery();

	        if (rs.next()) {
	        	memberDTO.setId(rs.getInt("id"));
	            memberDTO.setPass(rs.getString("pass"));
	            memberDTO.setName(rs.getString("name"));
	            memberDTO.setRegidate(rs.getDate("regidate"));
	        }
			success = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		String sqlstring = "UPDATE member SET pass = " + memberDTO.getPass() + ", name = " + memberDTO.getName() + ", regidate = now() WHERE id = " + id;
		map.put("method", "PUT");
		map.put("sqlstring", sqlstring);
		map.put("success", success);
		LogDAO logDAO = new LogDAO();
		logDAO.addLog(map);

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
			if(memberDTO.getName() != null) {
				psmt.setString(1, memberDTO.getName());
				psmt.setInt(2, id);
			}
			psmt.executeUpdate();
			
			String selectQuery = "SELECT * FROM member WHERE id = ?";
	        psmt = con.prepareStatement(selectQuery);
	        psmt.setInt(1, id);
	        rs = psmt.executeQuery();

	        if (rs.next()) {
	        	memberDTO.setId(rs.getInt("id"));
	            memberDTO.setPass(rs.getString("pass"));
	            memberDTO.setName(rs.getString("name"));
	            memberDTO.setRegidate(rs.getDate("regidate"));
	        }
	        
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("method", "PATCH");
		if(memberDTO.getPass() != null ) {
			String sqlstring = "UPDATE member SET pass = " + memberDTO.getPass() + ", regidate = now() WHERE id = " + id;
			map.put("sqlstring", sqlstring);
		}
		if(memberDTO.getName() != null) {
			String sqlstring = "UPDATE member SET name = " + memberDTO.getName() + ", regidate = now() WHERE id = " + id;
			map.put("sqlstring", sqlstring);
		}
		map.put("success", success);
		LogDAO logDAO = new LogDAO();
		logDAO.addLog(map);

		return memberDTO;
	}

	public void deleteMember(@PathVariable Integer id) {
		String query = "DELETE FROM member WHERE id = ?"; 

		try {
			// 쿼리 실행
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			success = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		String sqlstring = "DELETE FROM member WHERE id = " + id;
		map.put("method", "DELETE");
		map.put("sqlstring", sqlstring);
		map.put("success", success);
		LogDAO logDAO = new LogDAO();
		logDAO.addLog(map);
	}
}