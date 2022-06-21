package com.example.Tissue_back.controller.request;

import com.example.Tissue_back.entity.Performance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDto {
    private Long performNo;

    private String performName;

    private LocalDate performStart;

    private LocalDate performEnd;

    private LocalTime performTime;

    private int performPriceR;

    private int performPriceS;

    private int performPriceVip;

    private String performArea;

    private String performCategory;

    private String performGrade;

    private String performer;

    public Performance toEntity() {
        return Performance.builder()
                .performNo(performNo)
                .performName(performName)
                .performStart(performStart)
                .performEnd(performEnd)
                .performTime(performTime)
                .performPriceS(performPriceS)
                .performPriceR(performPriceR)
                .performPriceVip(performPriceVip)
                .performArea(performArea)
                .performCategory(performCategory)
                .performGrade(performGrade)
                .performer(performer)
                .build();
    }
}
