package com.example.Tissue_back.controller.request.member;

import com.example.Tissue_back.entity.member.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleModifyDto {

    private Long memberNo;
    private Role role;

}
