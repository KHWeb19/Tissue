package com.example.Tissue_back.controller.request.refund;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefundListResponse {
    private String ticketingDate;
    private String refundDate;
    private String memberId;
    private String performName;
    private String [] seatNameArr;
    private Integer finalPrice;
    private Integer usedMileage;
    private Integer usedCouponPrice;
    private String cancelCommission;
    private String refundStatus;
}
