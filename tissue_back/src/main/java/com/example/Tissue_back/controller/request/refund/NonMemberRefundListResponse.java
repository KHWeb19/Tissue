package com.example.Tissue_back.controller.request.refund;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NonMemberRefundListResponse {
    private Long refundNo;
    private String ticketingDate;
    private String refundDate;
    private String phone;
    private String [] seatNameArr;
    private String serial;
    private String refundStatus;
    private String performName;
    private Integer finalPrice;

}
