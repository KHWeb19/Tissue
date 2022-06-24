package com.example.Tissue_back.entity.coupon;

import com.example.Tissue_back.entity.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponNo;

    @Column
    private String couponName;

    @Column
    private String couponCategory;

    @Column
    private int couponPrice;

    @Column
    private String couponCondition;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate couponStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate couponEnd;

    @Column
    private String filename;

    @Column
    private String filepath;

    @ManyToOne
    @JoinColumn(name="member_no")
    private Member members;
}
