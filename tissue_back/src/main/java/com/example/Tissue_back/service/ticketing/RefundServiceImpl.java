package com.example.Tissue_back.service.ticketing;

import com.example.Tissue_back.controller.request.refund.RefundListResponse;
import com.example.Tissue_back.entity.coupon.Coupon;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.entity.ticketing.RefundRequest;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.repository.coupon.CouponRepository;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.repository.ticketing.RefundRepository;
import com.example.Tissue_back.repository.ticketing.TicketingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RefundServiceImpl implements RefundService{

    @Autowired
    private RefundRepository refundRepository;
    @Autowired
    private TicketingRepository ticketingRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private PerformanceRepository performanceRepository;

    @Override
    public void request (Long ticketingNo) {
        log.info("request ()");

        Optional<Ticketing> whatTicket = ticketingRepository.findById(ticketingNo);

        Ticketing ticketing = whatTicket.get();

        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setTicketing(ticketing);

        ticketing.setStatus("취소 대기");
        ticketingRepository.save(ticketing);

        refundRepository.save(refundRequest);

    }

    @Override
    public List<RefundListResponse> list() {
        List<RefundRequest> getRefundList = refundRepository.findAll();

        List<RefundListResponse> refundResponse = new ArrayList<>();

        for (int i = 0; i < getRefundList.size(); i ++){
            RefundRequest refundRequest = getRefundList.get(i);

            RefundListResponse refundListResponse = new RefundListResponse();

            refundListResponse.setTicketingDate(refundRequest.getTicketing().getTicketingRegDate());
            refundListResponse.setRefundDate(refundRequest.getRefundRegDate());
            refundListResponse.setMemberId(refundRequest.getTicketing().getMemberId());
            refundListResponse.setSeatNameArr(refundRequest.getTicketing().getSeatNameArr());
            refundListResponse.setFinalPrice(refundRequest.getTicketing().getFinalPrice());
            refundListResponse.setUsedMileage(refundRequest.getTicketing().getUsedMileage());

            Optional<Performance> getPerform = performanceRepository.findById(refundRequest.getTicketing().getPerformNo());
            Performance performance = getPerform.get();
            refundListResponse.setPerformName(performance.getPerformName());

            if(refundRequest.getTicketing().getUsedCouponNo() != null){
                Optional<Coupon> getCoupon = couponRepository.findById(refundRequest.getTicketing().getUsedCouponNo());
                Coupon coupon = getCoupon.get();

                refundListResponse.setUsedCouponPrice(coupon.getCouponPrice());
            }

            refundListResponse.setCancelCommission("날짜 비교해서 반환");
            refundListResponse.setRefundStatus(refundRequest.getTicketing().getStatus());

            refundResponse.add(refundListResponse);
        }

        return refundResponse;

    }
}
