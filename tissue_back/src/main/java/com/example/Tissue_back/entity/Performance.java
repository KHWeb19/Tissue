package com.example.Tissue_back.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="perform_no")
    private Long performNo;

    private String performName;

    // 시간도 같이 나옴
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date performStart;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date performEnd;

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

//    @Column(name="perform_detail_img")
//    private String performDetailImg;
//    @Column(name="perform_detail_img_path")
//    private String performDetailImgPath;
}
