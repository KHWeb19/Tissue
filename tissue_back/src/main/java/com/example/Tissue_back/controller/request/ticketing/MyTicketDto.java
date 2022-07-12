package com.example.Tissue_back.controller.request.ticketing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MyTicketDto {

    private String ticketing_no;
    private String performName;
    private String performThumbnail;
    private String performArea;
    private LocalDate performShowDate;

    private Integer final_price;
    private String reg_date;
    private String used_coupon;
    private String status;
    private Integer used_mileage;
    private String [] seat;

}
