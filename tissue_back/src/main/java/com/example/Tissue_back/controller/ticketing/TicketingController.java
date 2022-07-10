package com.example.Tissue_back.controller.ticketing;


import com.example.Tissue_back.controller.request.ticketing.Ticketing2Dto;
import com.example.Tissue_back.controller.request.ticketing.TicketingDto;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.entity.ticketing.TicketingSeat;
import com.example.Tissue_back.service.security.SecurityService;
import com.example.Tissue_back.service.ticketing.TicketingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util. *;

@Slf4j
@RestController
@RequestMapping("/ticketing")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class TicketingController {

    @Autowired
    private TicketingService ticketingService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/register")
    public void ticketingRegister(@Validated @RequestBody TicketingDto ticketingDto){
        log.info("ticketingRegister()");

        String memberId = securityService.getMemberId(ticketingDto.getMemberId());

        //예매 내역 저장
        ticketingService.register(ticketingDto,memberId);

        //예매 좌석 저장
        ticketingService.registerTicketingSeats(ticketingDto);

        //사용 마일리지 차감
        if(ticketingDto.getUsedMileage() != 0) {
            log.info("mileage minus");
            ticketingService.useMileage(ticketingDto,memberId);
        }
        //사용 쿠폰 삭제 / 내역 저장
        if(ticketingDto.getUsedCouponNo() != null){
            log.info("coupon minus");
            ticketingService.useCoupon(ticketingDto,memberId);
        }


    }

    @PostMapping("/register2")
    public void ticketing2Register(@Validated @RequestBody Ticketing2Dto ticketingDto){
        log.info("ticketing2Register()");
        //예매 내역 저장
        ticketingService.register2(ticketingDto);

        //예매 좌석 저장
        ticketingService.register2TicketingSeats(ticketingDto);

    }

    @GetMapping("/{performNo}")
    public List<TicketingSeat> ticketingList(@PathVariable("performNo")Long performNo){
        log.info("ticketingList()" + performNo);

        return ticketingService.ticketingList(performNo);
    }
}
