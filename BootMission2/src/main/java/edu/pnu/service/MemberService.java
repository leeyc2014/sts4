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
	
	public MemberService() {			// 데이터 초기화
		for(int i = 1; i <= 10; i++) {
			list.add(MemberDTO.builder()
					.id(i).name("name" + i).pass("pass" + i)
					.regidate(new Date()).build());
		}
	}
	
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
				memberDTO.setId(id);
				list.set(i, memberDTO);
				return memberDTO;
			}
		}
		return null;
	}
	
	public MemberDTO patchMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		for (MemberDTO member : list) {
			if (member.getId() == id) {
				member.setId(id);
				if (memberDTO.getName() != null) {
					member.setName(memberDTO.getName());
				}
				if (memberDTO.getPass() != null) {
					member.setPass(memberDTO.getPass());
				}
				if (memberDTO.getRegidate() != null) {
					member.setRegidate(memberDTO.getRegidate());
				}
				return member;
			}
		}
		return null;
	}
	
	public void deleteMember(@PathVariable Integer id) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id) {
				list.remove(i);
			}
		}
	}
}
