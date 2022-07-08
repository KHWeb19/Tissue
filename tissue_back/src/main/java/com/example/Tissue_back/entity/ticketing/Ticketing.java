package com.example.Tissue_back.entity.ticketing;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
@Entity
public class Ticketing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketingNo;

    @Column
    private String memberId;

    @Column
    private Long performNo;

    @Column
    private String []seatNameArr;

    @Column
    private Integer usedMileage;

    @Column
    private Long usedCouponNo;

    @CreatedDate
    private String reviewRegDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));

    @Column
    private Integer finalPrice;

    @Column
    public String status = "예매완료";
}
