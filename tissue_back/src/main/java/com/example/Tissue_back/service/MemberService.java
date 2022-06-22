package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.member.FindDto;
import com.example.Tissue_back.controller.request.member.LoginDto;
import com.example.Tissue_back.controller.request.member.MemberDto;
import com.example.Tissue_back.entity.member.Member;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public interface MemberService {
    public void register(MemberDto memberDto);
    public Boolean checkId(String memberId);
    public String login (LoginDto loginDto);
    public FindDto findId(FindDto findDto) throws CoolsmsException;
    public FindDto findPw(FindDto findDto) throws  CoolsmsException;
    public Member getInfo (String token);
    public Boolean checkPw (LoginDto check);
    public void modify (MemberDto memberDto);

}
