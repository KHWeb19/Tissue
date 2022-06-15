package com.example.Tissue_back.controller.request.member;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.member.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private String memberId;

    private String memberPw;

    private String memberName;

    private Date memberBirth;

    private String memberPhone;

    private String memberEmail;

    private String memberAddress;
    private String addZipCode;
    private String addDetail;

    private Role role;

    /* Dto -> Entity */
    public Member toEntity() {
        Member member = Member.builder()
                .memberId(memberId)
                .memberPw(memberPw)
                .memberName(memberName)
                .memberBirth(memberBirth)
                .memberPhone(memberPhone)
                .memberEmail(memberEmail)
                .memberAddress(memberAddress)
                .addZipCode(addZipCode)
                .addDetail(addDetail)
                .role(role.USER)
                .build();
        return member;
    }
}
