package com.example.Tissue_back.service.ticketing;

import com.example.Tissue_back.controller.request.ticketing.Ticketing2Dto;
import com.example.Tissue_back.controller.request.ticketing.TicketingDto;
import com.example.Tissue_back.entity.coupon.Coupon;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.entity.ticketing.Ticketing2;
import com.example.Tissue_back.entity.ticketing.TicketingSeat;
import com.example.Tissue_back.repository.coupon.CouponRepository;
import com.example.Tissue_back.repository.member.MemberRepository;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.repository.ticketing.Ticketing2Repository;
import com.example.Tissue_back.repository.ticketing.TicketingRepository;
import com.example.Tissue_back.repository.ticketing.TicketingSeatRepository;
import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class TicketingServiceImpl implements TicketingService{

    @Autowired
    private TicketingRepository ticketingRepository;
    @Autowired
    private Ticketing2Repository ticketing2Repository;

    @Autowired
    private TicketingSeatRepository ticketingSeatRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public void register(TicketingDto ticketingDto,String memberId) {
        //String memberId = securityService.getMemberId(ticketingDto.getMemberId());


        Ticketing ticketing = new Ticketing();

        ticketingDto.setMemberId(memberId);

        ticketing.setMemberId(ticketingDto.getMemberId());
        ticketing.setPerformNo(ticketingDto.getPerformNo());
        ticketing.setUsedMileage(ticketingDto.getUsedMileage());
        ticketing.setUsedCouponNo(ticketingDto.getUsedCouponNo());
        ticketing.setFinalPrice(ticketingDto.getFinalPrice());
        ticketing.setSeatNameArr(ticketingDto.getSeatNameArr());

        ticketingRepository.save(ticketing);


    }

    @Override
    public void registerTicketingSeats(TicketingDto ticketingDto) {
        TicketingSeat ticketingSeat = new TicketingSeat();
        String [] seatNameArr = ticketingDto.getSeatNameArr();
        Optional<Ticketing> findTicketing = ticketingRepository.findBySeat(seatNameArr);


        for (int i = 0; i < ticketingDto.getSeatNameArr().length; i++){
            ticketingSeat.setSeatName(ticketingDto.getSeatNameArr()[i]);
            ticketingSeat.setTicketing(findTicketing.get());
            ticketingSeat.setPerformNo(ticketingDto.getPerformNo());
            ticketingSeatRepository.save(ticketingSeat);
            ticketingSeat.setTicketingSeatNo(null);
        }


    }

    @Override
    public List<TicketingSeat> ticketingList(Long performNo) {

        return ticketingSeatRepository.findByPerformNo(performNo);
    }

    @Override
    public void useMileage(TicketingDto ticketingDto,String memberId) {
        //사용마일리지 차감


        log.info("useMileage()");
        memberRepository.updateMileage(memberId,ticketingDto.getUsedMileage());

        log.info("useMileage Suc");
    }

    @Override
    public void useCoupon(TicketingDto ticketingDto,String memberId) {
        //사용 쿠폰 삭제 / 내역 저장

        Optional<Member> findMember = memberRepository.findByMemberId(memberId);

        log.info("useCoupon mmm");
        Optional<Coupon> whatCoupon = couponRepository.findById(ticketingDto.getUsedCouponNo());

        Coupon coupon = whatCoupon.get();
        Member member = findMember.get();

        log.info("----"+member.getCoupons());

        member.getCoupons().remove(coupon);
        member.getUsed_coupons().add(coupon);

        memberRepository.save(member);

        log.info("useCoupon Suc");
    }

    @Override
    public void register2(Ticketing2Dto ticketingDto) {

        Ticketing2 ticketing = new Ticketing2();

        Random rand  = new Random();
        String serial = "";
        for(int i=0; i<8; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            serial += ran;
        }

        ticketing.setSerialNumber(serial);
        ticketing.setPhone(ticketingDto.getPhone());
        ticketing.setPerformNo(ticketingDto.getPerformNo());
        ticketing.setFinalPrice(ticketingDto.getFinalPrice());
        ticketing.setSeatNameArr(ticketingDto.getSeatNameArr());

        ticketing2Repository.save(ticketing);

    }
    @Override
    public void register2TicketingSeats(Ticketing2Dto ticketingDto) {
        TicketingSeat ticketingSeat = new TicketingSeat();
        String [] seatNameArr = ticketingDto.getSeatNameArr();
        Optional<Ticketing2> findTicketing = ticketing2Repository.findBySeat(seatNameArr);

        for (int i = 0; i < ticketingDto.getSeatNameArr().length; i++){
            ticketingSeat.setSeatName(ticketingDto.getSeatNameArr()[i]);
            ticketingSeat.setTicketing2(findTicketing.get());
            ticketingSeat.setPerformNo(ticketingDto.getPerformNo());
            ticketingSeatRepository.save(ticketingSeat);
            ticketingSeat.setTicketingSeatNo(null);
        }


    }

}
