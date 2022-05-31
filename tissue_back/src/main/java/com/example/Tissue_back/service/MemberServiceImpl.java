package com.example.Tissue_back.service;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(Member member) {

        String encodedPassword = passwordEncoder.encode(member.getMemberPw());
        member.setMemberPw(encodedPassword);

        repository.save(member);
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

}
