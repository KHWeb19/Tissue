package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.MemberDto;
import com.example.Tissue_back.entity.member.Member;

public interface MemberService {
    public void register(MemberDto memberDto);
    public Boolean checkId(String memberId);

}
