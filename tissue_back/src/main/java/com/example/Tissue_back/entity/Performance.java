package com.example.Tissue_back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
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

    private Date performStart;
    private Date performEnd;
    private Time performTime;

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
