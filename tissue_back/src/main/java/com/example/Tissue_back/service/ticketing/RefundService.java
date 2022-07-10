package com.example.Tissue_back.service.ticketing;


import com.example.Tissue_back.controller.request.refund.RefundListResponse;
import com.example.Tissue_back.entity.ticketing.RefundRequest;
import java.util.*;

public interface RefundService {

    public void request (Long ticketingNo);
    public List<RefundListResponse> list();
    public void acceptRefund(Long refundNo);
}
