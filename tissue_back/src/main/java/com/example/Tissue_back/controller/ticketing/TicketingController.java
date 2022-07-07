package com.example.Tissue_back.controller.ticketing;


import com.example.Tissue_back.controller.request.ticketing.TicketingDto;
import com.example.Tissue_back.entity.ticketing.Ticketing;
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

    @PostMapping("/register")
    public void ticketingRegister(@Validated @RequestBody TicketingDto ticketingDto){
        log.info("ticketingRegister()");

        //예매 내역 저장
        ticketingService.register(ticketingDto);

        //예매 좌석 저장
        ticketingService.registerTicketingSeats(ticketingDto);

        //사용 마일리지 차감
        if(ticketingDto.getUsedMileage() != 0) {
            ticketingService.useMileage(ticketingDto);
        }
        //사용 쿠폰 삭제 / 내역 저장
        if(ticketingDto.getUsedCouponNo() != null){
            ticketingService.useCoupon(ticketingDto);
        }
    }

    @GetMapping("/{performNo}")
    public List<Ticketing> ticketingList(@PathVariable("performNo")Long performNo){
        log.info("ticketingList()" + performNo);

        return ticketingService.ticketingList(performNo);
    }
}
