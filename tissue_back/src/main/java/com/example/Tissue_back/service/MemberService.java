package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.member.LoginDto;
import com.example.Tissue_back.controller.request.member.MemberDto;

public interface MemberService {
    public void register(MemberDto memberDto);
    public Boolean checkId(String memberId);
    public String login (LoginDto loginDto);

}
