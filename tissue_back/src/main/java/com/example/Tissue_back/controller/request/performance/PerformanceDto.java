package com.example.Tissue_back.controller.request.performance;

import com.example.Tissue_back.entity.performance.Performance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
