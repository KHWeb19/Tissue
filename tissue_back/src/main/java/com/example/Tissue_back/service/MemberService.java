package com.example.Tissue_back.service;

import com.example.Tissue_back.entity.member.Member;

public interface MemberService {
    public void register(Member member);
    public Boolean checkId(String memberId);

}
