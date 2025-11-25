package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;

@Service
public class MemberService {
	
	private MemberDAO memberDAO;

	@Autowired		// 생성자가 하나만 있다면 @Autowired 생략 가능
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public List<MemberDTO> getAllMember() {
        return memberDAO.getAllMember();
    }

    public MemberDTO getMemberById(@PathVariable Integer id) {
        return memberDAO.getMemberById(id);
    }

    public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
        return memberDAO.postMember(memberDTO);
    }

    public MemberDTO putMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
        return memberDAO.putMember(id, memberDTO);
    }

    public MemberDTO patchMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
        return memberDAO.patchMember(id, memberDTO);
    }

    public void deleteMember(@PathVariable Integer id) {
        memberDAO.deleteMember(id);
    }
}
