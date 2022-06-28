package com.example.Tissue_back.controller.member;

import com.example.Tissue_back.controller.request.member.RoleModifyDto;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.member.Role;
import com.example.Tissue_back.service.member.AdminMemberService;
import com.example.Tissue_back.service.security.SecurityService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins="http://localhost:8080", allowedHeaders = "*")
public class AdminMemberController {

    @Autowired
    private AdminMemberService service;

    @Autowired
    private SecurityService securityService;

    // 관리자 권한 판단
    @GetMapping("/role")
    public Boolean admin (@RequestParam(value="token") String token) {
        log.info("==is role admin?==" + token);
        Claims role = securityService.getRole(token);

        log.info("role" + role.get("roles"));
        if (!role.get("roles").toString().equals("ADMIN")){
            return false;
        }
        return true;
    }

    // 회원정보 전체 조회
    @GetMapping("/memberInfo")
    public List<Member> memberList () {
        log.info("== Admin inquire member List ==");
        return service.list();
    }

    // Role 수정
    @PutMapping("memberInfo/modify")
    public RoleModifyDto roleModify (@Validated @RequestBody RoleModifyDto roleModifyDto) {
        log.info("== modify Role ==" + roleModifyDto);

        service.roleModify(roleModifyDto);

        return roleModifyDto;
    }

    // 회원 강제 탈퇴
    @DeleteMapping("/{memberNo}")
    public void remove (@PathVariable("memberNo") Long memberNo) {
        log.info("remove()");

        service.remove(memberNo);
    }

}
