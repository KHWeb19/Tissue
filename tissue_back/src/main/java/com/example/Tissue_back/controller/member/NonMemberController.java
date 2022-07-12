package com.example.Tissue_back.controller.member;

import com.example.Tissue_back.controller.request.ticketing.MyTicketDto;
import com.example.Tissue_back.entity.ticketing.Ticketing2;
import com.example.Tissue_back.service.member.NonMemberService;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/nonMember")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class NonMemberController {

    @Autowired
    private NonMemberService nonMemberService;

    @PostMapping("rv/{serial}")
    public MyTicketDto ticketing2 (@PathVariable("serial")String serial){
        log.info("==find non Member Reservation==" + serial);

        return nonMemberService.checkSerial(serial);
    }

    @PostMapping("s/{phone}")
    public String [] findSerial (@PathVariable("phone") String phone) throws CoolsmsException {
        log.info("==find non Member serial==" + phone);

        return nonMemberService.findSerial(phone);
    }

    @PostMapping("/request/{ticketingNo}")
    public void refundRequest (@PathVariable("ticketingNo") Long ticketingNo) {
        log.info("== ticket Refund Request ==" + ticketingNo);

        nonMemberService.request(ticketingNo);
    }

}
