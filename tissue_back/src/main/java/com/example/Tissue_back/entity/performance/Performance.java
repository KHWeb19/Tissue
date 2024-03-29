package com.example.Tissue_back.entity.performance;


import com.example.Tissue_back.controller.request.performance.PerformanceDto;

import com.example.Tissue_back.entity.event.Event;
import com.example.Tissue_back.entity.review.Review;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import java.util.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="perform_no")
    private Long performNo;

    private String performName;

//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate performStart;

//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate performEnd;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime performTime;

    @Column(name="perform_price_r")
    private int performPriceR;
    @Column(name="perform_price_s")
    private int performPriceS;
    @Column(name="perform_price_vip")
    private int performPriceVip;

    private String performArea;
    private String performCategory;
    private String performGrade;
    private String performer;

    @Column(name="perform_thumbnail")
    private String performThumbnail;
    @Column(name="perform_thumbnail_path")
    private String performThumbnailPath;

    @Column(name="perform_detail_img_1")
    private String performDetailImg1;
    @Column(name="perform_detail_img_2")
    private String performDetailImg2;
    @Column(name="perform_detail_img_3")
    private String performDetailImg3;
    @Column(name="perform_detail_img_4")
    private String performDetailImg4;
    @Column(name="perform_detail_img_5")
    private String performDetailImg5;

    @Column(name="perform_detail_img_path")
    private String performDetailImgPath;

    @CreationTimestamp
    private Date performRegDate;

    public void updatePerformance(PerformanceDto performanceDto) {
        this.performName = performanceDto.getPerformName();
        this.performStart = performanceDto.getPerformStart();
        this.performEnd = performanceDto.getPerformEnd();
        this.performTime = performanceDto.getPerformTime();
        this.performPriceS = performanceDto.getPerformPriceS();
        this.performPriceR = performanceDto.getPerformPriceR();
        this.performPriceVip = performanceDto.getPerformPriceVip();
        this.performArea = performanceDto.getPerformArea();
        this.performCategory = performanceDto.getPerformCategory();
        this.performGrade = performanceDto.getPerformGrade();
        this.performer = performanceDto.getPerformer();
    }

    //임지훈
    @Column
    private String hallName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate performShowDate;

    @OneToMany(mappedBy = "performance", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SUBSELECT)
    private List<Review> reviewList;



    // 유아림
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "performance", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Likes> performLike = new HashSet<>();



/*
    @OneToOne(mappedBy = "performance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Event event;
*/

}
