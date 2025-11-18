package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;

@RestController
@RequestMapping("/api")
public class MemberController {
	// 데이터 저장용 컬렉션 객체 생성
	private List<MemberDTO> list = new ArrayList<>();

	public MemberController() { // 데이터 초기화
		for (int i = 1; i <= 10; i++) {
			list.add(MemberDTO.builder().id(i).name("name" + i).pass("pass" + i).regidate(new Date()).build());
		}
	}

	@GetMapping("/member") // 검색(Read - select All)
	public List<MemberDTO> getAllMember() {
		return list;
	}

	@GetMapping("/member/{id}") // 검색(Read - select One)
	public MemberDTO getMemberById(@PathVariable Integer id) {
		for (MemberDTO member : list) {
			if (member.getId() == id) {
				return member;
			}
		}
		return null;
	}

	@PostMapping("/member") // 입력(Create - insert)
	public MemberDTO postMember(MemberDTO memberDTO) {
		// id는 새로 생성, regidate는 현재 시간 날짜로 설정
		int max = -1;
		for (MemberDTO member : list) {
			if (max < member.getId()) {
				max = member.getId();
			}
		}
		memberDTO.setId(max + 1);
		memberDTO.setRegidate(new Date());
		list.add(memberDTO);

		return memberDTO;
	}

	@PutMapping("/member/{id}") // 수정(Update - 객체 교체)
	public MemberDTO putMember(@PathVariable Integer id, MemberDTO memberDTO) {
		// memberDTO의 id를 파라미터 id로 설정
		memberDTO.setId(id);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				list.set(i, memberDTO);
				return memberDTO;
			}
		}
		return null;
	}

	@PatchMapping("/member/{id}") // 수정(Update - 일부 정보 수정)
	public MemberDTO patchMember(@PathVariable Integer id, MemberDTO memberDTO) {
		// memberDTO에서 null이 아닌 필드만 수정
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

	@DeleteMapping("/member/{id}") // 삭제(Delete - delete)
	public void deleteMember(@PathVariable Integer id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				list.remove(i);

			}
		}
	}
}
