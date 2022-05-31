package com.example.Tissue_back.controller;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/Member")
@CrossOrigin(origins="http://localhost:8080", allowedHeaders = "*")
public class MemberController {

    @Autowired
    private MemberService service;

    // 회원가입
    @PostMapping("/register")
    public Member register (@Validated @RequestBody Member member) {
        log.info("== Tissue Member Register ==" + member);

        service.register(member);

        return member;
    }

    // 아이디 중복 여부 체크
    @GetMapping("/checkId/{id}")
    public Boolean checkId (@PathVariable("id") String memberId){
        log.info("checkId(): " + memberId);

        Boolean checkId = service.checkId(memberId);

        return checkId;
    }
}
