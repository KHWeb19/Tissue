package com.example.Tissue_back.controller.request.ranking;

import com.example.Tissue_back.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RankingDTO {

    private Long ranking;
    private Long performNo;
    private Long COUNT;


    private String performName;
    private LocalDate performStart;
    private LocalDate performEnd;
    private String performThumbnail;

    private String hallName;

    private Integer reviewRating;



    public RankingDTO (BigInteger ranking, BigInteger performNo, BigInteger COUNT, String performName, Date performStart, Date performEnd,
                         String performThumbnail, String hallName, Integer reviewRating) {

        this.ranking = ranking.longValue();
        this.performNo = performNo.longValue();
        this.COUNT = COUNT.longValue();


        this.performName = performName;
        this.performStart = performStart.toLocalDate();
        this.performEnd = performEnd.toLocalDate();
        this.performThumbnail = performThumbnail;

        this.hallName = hallName;

        this.reviewRating = reviewRating;
    }


}
