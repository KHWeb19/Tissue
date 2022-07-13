package com.example.Tissue_back.service.member;

import com.example.Tissue_back.controller.request.ticketing.MyTicketDto;
import com.example.Tissue_back.entity.coupon.Coupon;
import com.example.Tissue_back.entity.map.Map;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.entity.ticketing.RefundRequest;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.entity.ticketing.Ticketing2;
import com.example.Tissue_back.repository.map.MapRepository;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.repository.ticketing.RefundRepository;
import com.example.Tissue_back.repository.ticketing.Ticketing2Repository;
import com.example.Tissue_back.service.PhoneCheckService;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NonMemberServiceImpl implements NonMemberService {
    
    @Autowired
    Ticketing2Repository ticketing2Repository;
    @Autowired
    PerformanceRepository performanceRepository;
    @Autowired
    MapRepository mapRepository;
    @Autowired
    RefundRepository refundRepository;

    @Autowired
    PhoneCheckService phoneCheckService;

    @Transactional
    @Override
    public MyTicketDto checkSerial (String serialNumber) {
        log.info("check Serial()");

        Optional<Ticketing2> findTicket = ticketing2Repository.findBySerialNumber(serialNumber);

        Ticketing2 ticketing2 = findTicket.get();

        log.info("ticket" + ticketing2);

        Optional<Performance> getPerform = performanceRepository.findById(ticketing2.getPerformNo());
        Performance performance = getPerform.get();

        Optional<Map> getMap = mapRepository.findById(ticketing2.getPerformNo());
        Map map = getMap.get();

        MyTicketDto ticketDto = new MyTicketDto();

        ticketDto.setTicketing_no(ticketing2.getTicketingNo());
        ticketDto.setSerial(ticketing2.getSerialNumber());
        ticketDto.setFinal_price(ticketing2.getFinalPrice());
        ticketDto.setReg_date(ticketing2.getTicketingRegDate());
        ticketDto.setSeat(ticketing2.getSeatNameArr());
        ticketDto.setPerformShowDate(performance.getPerformShowDate());
        ticketDto.setPerformArea(map.getName());
        ticketDto.setPerformThumbnail(performance.getPerformThumbnail());
        ticketDto.setPerformName(performance.getPerformName());
        ticketDto.setStatus(ticketing2.getStatus());

        log.info("!!!!!!!!!!!!!!!!!!!!!!" + ticketDto);
        return ticketDto;
    }
    
    @Override
    public String [] findSerial (String phone) throws CoolsmsException {
        List<Ticketing2> find = ticketing2Repository.findByPhone(phone);
        String [] serialSet = new String[find.size()+1];

        for(int i = 0; i <find.size(); i++){
            Ticketing2 ticketing2 = find.get(i);
            
            serialSet[i] = ticketing2.getSerialNumber();
        }
        serialSet[find.size()] = phoneCheckService.PhoneNumberCheck(phone);

        return  serialSet;
    }

    @Override
    public void request (Long ticketingNo) {
        log.info("request ()");

        Optional<Ticketing2> whatTicket = ticketing2Repository.findById(ticketingNo);

        Ticketing2 ticketing = whatTicket.get();

        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setTicketing2(ticketing);

        ticketing.setStatus("취소 대기");
        ticketing2Repository.save(ticketing);

        refundRepository.save(refundRequest);

    }

}
