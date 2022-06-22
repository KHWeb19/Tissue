package com.example.Tissue_back.entity.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(nullable = false)
    @Builder.Default
    private String memberGrade = "일반";

    @Column(nullable = false)
    @Builder.Default
    private int memberMileage = 0;

    @Column(nullable = false, length = 12)
    private String memberId;

    @Column(nullable = false)
    private String memberPw;

    @Column(nullable = false, length = 7)
    private String memberName;

    @Column
    private LocalDate memberBirth;

    @Column(nullable = false)
    private String memberPhone;

    @Column(nullable = false)
    private String memberEmail;

    @Column(nullable = false)
    private String memberAddress;
    @Column(nullable = false)
    private String addZipCode;
    @Column(nullable = false)
    private String addDetail;

    @CreationTimestamp
    private LocalDate memberRegDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
