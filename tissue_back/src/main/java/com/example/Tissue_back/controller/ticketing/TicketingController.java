package com.example.Tissue_back.controller.ticketing;


import com.example.Tissue_back.controller.request.ticketing.TicketingDto;
import com.example.Tissue_back.service.ticketing.TicketingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

        ticketingService.register(ticketingDto);
    }
}
