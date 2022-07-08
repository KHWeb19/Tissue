package com.example.Tissue_back.service.ticketing;

import com.example.Tissue_back.entity.ticketing.RefundRequest;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.repository.ticketing.RefundRepository;
import com.example.Tissue_back.repository.ticketing.TicketingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RefundServiceImpl implements RefundService{

    @Autowired
    private RefundRepository refundRepository;
    @Autowired
    private TicketingRepository ticketingRepository;

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
}
