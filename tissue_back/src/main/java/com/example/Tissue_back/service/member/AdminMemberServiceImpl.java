package com.example.Tissue_back.service.member;

import com.example.Tissue_back.controller.request.member.RoleModifyDto;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminMemberServiceImpl implements AdminMemberService{

    @Autowired
    private MemberRepository repository;

    @Override
    public List<Member> list(){
        return repository.findAll(Sort.by(Sort.Direction.DESC,"memberNo"));
    }

    @Override
    public void roleModify(RoleModifyDto roleModifyDto) {
        Optional<Member> findMember= repository.findById(roleModifyDto.getMemberNo());

        Member member = findMember.get();
        member.setRole(roleModifyDto.getRole());

        repository.save(member);
    }

    @Override
    public void remove (Long memberNo) {
        repository.deleteById(Long.valueOf(memberNo));
    }

}
