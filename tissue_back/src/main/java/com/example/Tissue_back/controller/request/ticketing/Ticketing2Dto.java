package com.example.Tissue_back.controller.request.ticketing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
@NoArgsConstructor
public class Ticketing2Dto {
    private Long ticketingNo;
    private String phone;
    private Long performNo;
    private String [] seatNameArr;
    private String ticketingDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    private Integer finalPrice;
}