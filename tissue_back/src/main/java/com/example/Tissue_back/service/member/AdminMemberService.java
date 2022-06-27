package com.example.Tissue_back.service.member;

import com.example.Tissue_back.controller.request.member.RoleModifyDto;
import com.example.Tissue_back.entity.member.Member;

import java.util.List;

public interface AdminMemberService {

    public List<Member> list();
    public void roleModify(RoleModifyDto roleModifyDto);
    public void remove (Long memberNo);
}
