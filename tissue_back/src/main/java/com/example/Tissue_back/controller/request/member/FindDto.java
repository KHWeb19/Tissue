package com.example.Tissue_back.controller.request.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindDto {

    private String memberName;
    private String memberId;
    private String memberPw;
    private String memberPhone;
    private String numStr;
}
