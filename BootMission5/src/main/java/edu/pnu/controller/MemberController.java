package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {
	
    private final MemberService memberService;
    
    @Autowired		// 생성자가 하나만 있다면 @Autowired 생략 가능
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member")
    public List<MemberDTO> getAllMember() {
        return memberService.getAllMember();
    }

    @GetMapping("/member/{id}")
    public MemberDTO getMemberById(@PathVariable Integer id) {
        return memberService.getMemberById(id);
    }

    @PostMapping("/member")
    public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
        return memberService.postMember(memberDTO);
    }

    @PutMapping("/member/{id}")
    public MemberDTO putMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
        return memberService.putMember(id, memberDTO);
    }

    @PatchMapping("/member/{id}")
    public MemberDTO patchMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
        return memberService.patchMember(id, memberDTO);
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
    }
}
