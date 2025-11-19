package edu.pnu.service;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;

@RestController
@RequestMapping("/api")
public class MemberService {
	
	private MemberDAO memberDAO;

    public MemberService() {
        this.memberDAO = new MemberDAO();
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
