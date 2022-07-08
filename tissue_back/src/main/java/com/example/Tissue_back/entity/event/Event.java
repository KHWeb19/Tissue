package com.example.Tissue_back.entity.event;

import com.example.Tissue_back.controller.request.event.EventDto;
import com.example.Tissue_back.entity.performance.Performance;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_no")
    private Long eventNo;

    @OneToOne
    @JoinColumn(name="perform_no")
    private Performance performance;

    private String eventTitle;
    private String eventCategory;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventEnd;

    @CreationTimestamp
    private Date eventRegDate;

    @Column
    private String eventContent;

    @Column
    private String eventGoods;

    @Column
    private Integer eventWinnerCnt;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventWinnerDate;

}
