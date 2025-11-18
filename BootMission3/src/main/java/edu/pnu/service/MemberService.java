package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import edu.pnu.domain.MemberDTO;

@RestController
@RequestMapping("/api")
public class MemberService {
	
	// 데이터 저장용 컬렉션 객체 생성
	private List<MemberDTO> list = new ArrayList<>();
	
	public List<MemberDTO> getAllMember() {
		return list;
	}
	
	public MemberDTO getMemberById(@PathVariable Integer id) {
		for(MemberDTO member : list) {
			if(member.getId() == id) {
				return member;
			}
		}
		return null;
	}
	
	public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
		int max = -1;
		for(MemberDTO member:list) {
			if(max < member.getId()) {
				max = member.getId();
			}			
		}
		memberDTO.setId(max + 1);
		memberDTO.setRegidate(new Date());
		list.add(memberDTO);
		return memberDTO;
	}
	
	public MemberDTO putMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id) {
				list.set(i, memberDTO);
				return memberDTO;
			}
		}
		return null;
	}
	
	public MemberDTO patchMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		for(MemberDTO member : list) {
			member.setId(id);
			if(member.getName() != null) {
				member.setName(memberDTO.getName());
			}
			if(member.getPass() != null) {
				member.setPass(memberDTO.getPass());
			}
			if(member.getRegidate() != null) {
				member.setRegidate(memberDTO.getRegidate());
			}
			return member;
		}
		return null;
	}
	
	public void deleteMember(@PathVariable Integer id) {
		for(int i = 0; i <= list.size(); i++) {
			if(list.get(i).getId() == id) {
				list.remove(i);
			}
		}
	}
}
