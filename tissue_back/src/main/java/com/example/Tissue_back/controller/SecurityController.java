package com.example.Tissue_back.controller;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.repository.member.MemberRepository;
import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/security")
@CrossOrigin(origins="http://localhost:8080", allowedHeaders = "*")
public class SecurityController {

    @Autowired
    SecurityService securityService;
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/check")
    public String check (@RequestParam(value = "token") String token,
                            @RequestParam(value = "refreshToken") String refreshToken) {
        log.info(token);

        if (securityService.validateToken(token) == SecurityService.JwtCode.ACCESS) {
            return "ACCESS";
        } else {
            log.info("Token Reissue");
            Optional<Member> getMember = memberRepository.findByRefreshToken(refreshToken);
            Member member = getMember.get();

            if (securityService.validateToken(refreshToken) == SecurityService.JwtCode.ACCESS) {
                String newToken = securityService.createToken(member.getMemberId(), member.getMemberNo(), member.getRole(), (1 * 1000 * 60));
                log.info("new !!!!!!!!!!!!!!" + newToken);
                return newToken;
            } else {
                member.setRefreshToken(null);
                memberRepository.save(member);
                return "EXPIRED";
            }
        }
    }
}
