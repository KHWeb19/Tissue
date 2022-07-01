package com.example.Tissue_back.controller.request.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long eventNo;
    private Long performNo;
    private String eventTitle;
    private String eventCategory;
    private LocalDate eventStart;
    private LocalDate eventEnd;
    private Date eventRegDate;
    private String eventContent;
    private String eventGoods;
    private Integer eventWinnerCnt;
    private LocalDate eventWinnerDate;

}
