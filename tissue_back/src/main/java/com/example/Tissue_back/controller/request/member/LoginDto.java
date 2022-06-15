package com.example.Tissue_back.controller.request.member;

import com.example.Tissue_back.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String memberId;

    private String memberPw;

    public Member toEntity() {
        Member build = Member.builder()
                .memberId(memberId)
                .memberPw(memberPw)
                .build();
        return build;
    }
}
