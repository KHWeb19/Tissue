package com.example.Tissue_back.service.ticketing;

import com.example.Tissue_back.controller.request.ticketing.TicketingDto;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.entity.ticketing.TicketingSeat;

import java.util. *;

public interface TicketingService {
    public void register(TicketingDto ticketingDto, String memberId);
    public void registerTicketingSeats(TicketingDto ticketingDto);
    public List<TicketingSeat> ticketingList(Long performNo);
    public void useMileage(TicketingDto ticketingDto, String memberId);
    public void useCoupon(TicketingDto ticketingDto, String memberId);
}
