package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.member.FindDto;
import com.example.Tissue_back.controller.request.member.LoginDto;
import com.example.Tissue_back.controller.request.member.MemberDto;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.repository.member.MemberRepository;
import com.example.Tissue_back.service.security.SecurityService;
import com.example.Tissue_back.util.RandomPassword;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private SecurityService securityService;
    @Autowired
    private PhoneCheckService phoneCheckService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(MemberDto memberDto) {

        String encodedPassword = passwordEncoder.encode(memberDto.getMemberPw());
        memberDto.setMemberPw(encodedPassword);

        repository.save(memberDto.toEntity());
    }

    @Override
    public Boolean checkId(String memberId) {
        Optional<Member> checkId = repository.findByMemberId(memberId);

        if (checkId.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String login(LoginDto loginDto) {
        Optional<Member> maybeMember = repository.findByMemberId(loginDto.getMemberId());

        if(maybeMember.equals(Optional.empty())){
            log.info("Id값이 없습니다.");
            return null;
        }

        Member loginMember = maybeMember.get();

        if (!passwordEncoder.matches(loginDto.getMemberPw(), loginMember.getMemberPw())) {
            log.info("Entered wrong password!");
            return null;
        }

        String encodedPassword = passwordEncoder.encode(loginDto.getMemberPw());

        String token = securityService.createToken(loginDto.getMemberId(), loginMember.getRole(), (10*1000*60));

        return token;
    }

    @Override
    public FindDto findId (FindDto findDto) throws CoolsmsException {
        Optional<Member> findMember = repository.findByMemberName(findDto.getMemberName());

        if(findMember.equals(Optional.empty())) {
            log.info("not exist member");
            return null;
        }

        Member member = findMember.get();

        if(!Objects.equals(member.getMemberPhone(), findDto.getMemberPhone())) {
            log.info("wrong phone number");
            return null;
        }

        findDto.setMemberId(member.getMemberId());
        findDto.setNumStr(phoneCheckService.PhoneNumberCheck(findDto.getMemberPhone()));

        return findDto;
    }

    @Override
    public FindDto findPw (FindDto findDto) throws CoolsmsException {
        Optional<Member> findMember = repository.findByMemberId(findDto.getMemberId());

        if(findMember.equals(Optional.empty())) {
            log.info("not exist member");
            return null;
        }

        Member member = findMember.get();

        if(!Objects.equals(member.getMemberPhone(), findDto.getMemberPhone())) {
            log.info("wrong phone number");
            return null;
        }

        String newPw = RandomPassword.randomPassword(10);
        findDto.setMemberPw(newPw);

        String encodedPassword = passwordEncoder.encode(newPw);
        member.setMemberPw(encodedPassword);
        repository.save(member);

        findDto.setNumStr(phoneCheckService.PhoneNumberCheck(findDto.getMemberPhone()));

        return findDto;
    }

    @Override
    public Member getInfo (String token) {

        String memberId = securityService.getMemberId(token);
        log.info("check >>>>" + memberId);

        Optional<Member> findMember = repository.findByMemberId(memberId);

        return findMember.get();
    }

}
