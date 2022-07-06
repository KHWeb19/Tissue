package com.example.Tissue_back.service.ticketing;

import com.example.Tissue_back.controller.request.ticketing.TicketingDto;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.repository.member.MemberRepository;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.repository.ticketing.TicketingRepository;
import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TicketingServiceImpl implements TicketingService{

    @Autowired
    private TicketingRepository ticketingRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public void register(TicketingDto ticketingDto) {
        String memberId = securityService.getMemberId(ticketingDto.getMemberId());

        Ticketing ticketing = new Ticketing();

        ticketingDto.setMemberId(memberId);

        ticketing.setMemberId(ticketingDto.getMemberId());
        ticketing.setPerformNo(ticketingDto.getPerformNo());
        ticketing.setUsedMileage(ticketingDto.getUsedMileage());
        ticketing.setUsedCouponNo(ticketingDto.getUsedCouponNo());
        ticketing.setFinalPrice(ticketingDto.getFinalPrice());
        ticketing.setSeatNameArr(ticketingDto.getSeatNameArr());

        ticketingRepository.save(ticketing);
        memberRepository.updateMileage(memberId,ticketingDto.getUsedMileage());
    }
}
