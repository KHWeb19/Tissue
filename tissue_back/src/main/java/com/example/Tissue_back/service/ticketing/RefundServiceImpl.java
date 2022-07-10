package com.example.Tissue_back.service.ticketing;

import com.example.Tissue_back.controller.request.refund.NonMemberRefundListResponse;
import com.example.Tissue_back.controller.request.refund.RefundListResponse;
import com.example.Tissue_back.entity.coupon.Coupon;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.entity.ticketing.RefundRequest;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.repository.coupon.CouponRepository;
import com.example.Tissue_back.repository.member.MemberRepository;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.repository.ticketing.RefundRepository;
import com.example.Tissue_back.repository.ticketing.TicketingRepository;
import com.example.Tissue_back.repository.ticketing.TicketingSeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TicketingSeatRepository ticketingSeatRepository;

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
            if(getRefundList.get(i).getTicketing() != null) {
                RefundRequest refundRequest = getRefundList.get(i);

                RefundListResponse refundListResponse = new RefundListResponse();

                refundListResponse.setRefundNo(refundRequest.getRefundNo());
                refundListResponse.setTicketingDate(refundRequest.getTicketing().getTicketingRegDate());
                refundListResponse.setRefundDate(refundRequest.getRefundRegDate());
                refundListResponse.setMemberId(refundRequest.getTicketing().getMemberId());
                refundListResponse.setSeatNameArr(refundRequest.getTicketing().getSeatNameArr());
                refundListResponse.setFinalPrice(refundRequest.getTicketing().getFinalPrice());
                refundListResponse.setUsedMileage(refundRequest.getTicketing().getUsedMileage());

                Optional<Performance> getPerform = performanceRepository.findById(refundRequest.getTicketing().getPerformNo());
                Performance performance = getPerform.get();
                refundListResponse.setPerformName(performance.getPerformName());

                if (refundRequest.getTicketing().getUsedCouponNo() != null) {
                    Optional<Coupon> getCoupon = couponRepository.findById(refundRequest.getTicketing().getUsedCouponNo());
                    Coupon coupon = getCoupon.get();

                    refundListResponse.setUsedCouponPrice(coupon.getCouponPrice());
                }


                refundListResponse.setCancelCommission("날짜 비교해서 반환");
                refundListResponse.setRefundStatus(refundRequest.getTicketing().getStatus());

                refundResponse.add(refundListResponse);
            }
        }

        return refundResponse;

    }

    @Override
    public List<NonMemberRefundListResponse> nonMemberList() {
        List<RefundRequest> getRefundList = refundRepository.findAll();

        List<NonMemberRefundListResponse> nonMemberRefundListResponses = new ArrayList<>();

        for (int i = 0; i < getRefundList.size(); i ++){
            if(getRefundList.get(i).getTicketing2() != null) {
                RefundRequest refundRequest = getRefundList.get(i);

                NonMemberRefundListResponse nonMemberRefundListResponse = new NonMemberRefundListResponse();

                nonMemberRefundListResponse.setRefundNo(refundRequest.getRefundNo());
                nonMemberRefundListResponse.setTicketingDate(refundRequest.getTicketing2().getTicketingRegDate());
                nonMemberRefundListResponse.setRefundDate(refundRequest.getRefundRegDate());
                nonMemberRefundListResponse.setPhone(refundRequest.getTicketing2().getPhone());
                nonMemberRefundListResponse.setSeatNameArr(refundRequest.getTicketing2().getSeatNameArr());
                nonMemberRefundListResponse.setFinalPrice(refundRequest.getTicketing2().getFinalPrice());
                nonMemberRefundListResponse.setSerial(refundRequest.getTicketing2().getSerialNumber());

                Optional<Performance> getPerform = performanceRepository.findById(refundRequest.getTicketing2().getPerformNo());
                Performance performance = getPerform.get();
                nonMemberRefundListResponse.setPerformName(performance.getPerformName());


                nonMemberRefundListResponse.setRefundStatus(refundRequest.getTicketing2().getStatus());

                nonMemberRefundListResponses.add(nonMemberRefundListResponse);
            }
        }

        return nonMemberRefundListResponses;
    }

    @Override
    public void acceptRefund(Long refundNo) {

        Optional<RefundRequest> findRefundInfo = refundRepository.findById(refundNo);
        RefundRequest getRefundInfo = findRefundInfo.get();

        if (getRefundInfo.getTicketing() != null){
            //마일리지 복구
            if (getRefundInfo.getTicketing().getUsedMileage() != 0){
                memberRepository.refundMileage(getRefundInfo.getTicketing().getMemberId(), getRefundInfo.getTicketing().getUsedMileage());
            }

            //쿠폰 사용내역 삭제 -> 쿠폰 복구
            if (getRefundInfo.getTicketing().getUsedCouponNo() != null) {
                Optional<Member> findMember = memberRepository.findByMemberId(getRefundInfo.getTicketing().getMemberId());
                Optional<Coupon> findCoupon = couponRepository.findById(getRefundInfo.getTicketing().getUsedCouponNo());

                Coupon coupon = findCoupon.get();
                Member member = findMember.get();

                member.getUsed_coupons().remove(coupon);
                member.getCoupons().add(coupon);

                memberRepository.save(member);
            }
            //환불 상태 변경
            String status = "취소완료";
            ticketingRepository.changeStatus(getRefundInfo.getTicketing().getTicketingNo(),status);

            //좌석 삭제
            ticketingSeatRepository.deleteByTicketingNo(getRefundInfo.getTicketing());
        }

        if (getRefundInfo.getTicketing2() != null){

            //환불 상태 변경
            String status = "취소완료";
            ticketingRepository.changeNonMemberStatus(getRefundInfo.getTicketing2().getTicketingNo(),status);

            //좌석 삭제
            ticketingSeatRepository.deleteNonMemberByTicketingNo(getRefundInfo.getTicketing2());
        }




    }
}
