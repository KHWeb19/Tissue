package com.example.Tissue_back.controller.request.review;

import com.example.Tissue_back.entity.performance.Performance;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
@NoArgsConstructor
public class MyReviewDto {

    private Long reviewNo;

    private String reviewWriter;

    private String reviewContent;

    private String reviewRegDate;

    private Integer reviewRating;

    private String performThumbnail;

    private String performName;
}
