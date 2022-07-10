package com.example.Tissue_back.service.member;

import com.example.Tissue_back.controller.request.ticketing.MyTicketDto;
import com.example.Tissue_back.entity.ticketing.Ticketing2;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public interface NonMemberService {
    public MyTicketDto checkSerial (String serialNumber);
    public String [] findSerial (String phone) throws CoolsmsException;
    public void request (Long ticketingNo);
}
