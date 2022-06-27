package com.example.Tissue_back.entity.member;

import com.example.Tissue_back.entity.coupon.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @Builder.Default
    private List<Coupon> coupons = new ArrayList<>();

}
