package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {

    private MemberService memberService;

    public MemberController() {
        this.memberService = new MemberService();
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
