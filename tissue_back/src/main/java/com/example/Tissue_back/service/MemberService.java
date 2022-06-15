package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.member.FindDto;
import com.example.Tissue_back.controller.request.member.LoginDto;
import com.example.Tissue_back.controller.request.member.MemberDto;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.apache.commons.digester.plugins.strategies.FinderFromClass;

public interface MemberService {
    public void register(MemberDto memberDto);
    public Boolean checkId(String memberId);
    public String login (LoginDto loginDto);
    public FindDto findId(FindDto findDto) throws CoolsmsException;
    public FindDto findPw(FindDto findDto) throws  CoolsmsException;

}
