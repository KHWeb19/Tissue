package com.example.Tissue_back.controller.ticketing;


import com.example.Tissue_back.controller.request.refund.RefundListResponse;
import com.example.Tissue_back.entity.ticketing.RefundRequest;
import com.example.Tissue_back.service.ticketing.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/refund")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @PostMapping("/requset/{ticketingNo}")
    public void refundRequest (@PathVariable("ticketingNo") Long ticketingNo) {
        log.info("== ticket Refund Request ==" + ticketingNo);

        refundService.request(ticketingNo);
    }

    @GetMapping("/list")
    public List<RefundListResponse> refundList(){
        log.info("refundList()");

        return refundService.list();
    }
}
