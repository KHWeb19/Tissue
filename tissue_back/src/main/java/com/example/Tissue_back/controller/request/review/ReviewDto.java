package com.example.Tissue_back.controller.request.review;

import com.example.Tissue_back.entity.performance.Performance;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
@NoArgsConstructor
public class ReviewDto {
    private String reviewNo;
    private String reviewWriter;
    private String reviewContent;
    private String reviewRegDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    private Integer reviewRating;
    private Performance performance;
}
