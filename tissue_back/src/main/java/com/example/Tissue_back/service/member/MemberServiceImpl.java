package com.example.Tissue_back.service.member;

import com.example.Tissue_back.controller.request.member.FindDto;
import com.example.Tissue_back.controller.request.member.LoginDto;
import com.example.Tissue_back.controller.request.member.MemberDto;
import com.example.Tissue_back.controller.request.review.MyReviewDto;
import com.example.Tissue_back.controller.request.ticketing.MyTicketDto;
import com.example.Tissue_back.controller.request.ticketing.TicketingDto;
import com.example.Tissue_back.entity.coupon.Coupon;
import com.example.Tissue_back.entity.map.Map;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Likes;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.entity.qna.Qna;
import com.example.Tissue_back.entity.review.Review;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.repository.LikesRepository;
import com.example.Tissue_back.repository.coupon.CouponRepository;
import com.example.Tissue_back.repository.map.MapRepository;
import com.example.Tissue_back.repository.member.MemberRepository;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.repository.qna.QnaRepository;
import com.example.Tissue_back.repository.review.ReviewRepository;
import com.example.Tissue_back.repository.ticketing.TicketingRepository;
import com.example.Tissue_back.service.PhoneCheckService;
import com.example.Tissue_back.service.security.SecurityService;
import com.example.Tissue_back.util.RandomPassword;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private SecurityService securityService;
    @Autowired
    private PhoneCheckService phoneCheckService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private QnaRepository qnaRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    private TicketingRepository ticketingRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private MapRepository mapRepository;
    @Autowired
    private LikesRepository likesRepository;

    @Override
    public void register(MemberDto memberDto) {

        String encodedPassword = passwordEncoder.encode(memberDto.getMemberPw());
        memberDto.setMemberPw(encodedPassword);

        repository.save(memberDto.toEntity());
    }

    @Override
    public Boolean checkId(String memberId) {
        Optional<Member> checkId = repository.findByMemberId(memberId);

        if (checkId.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String login(LoginDto loginDto) {
        Optional<Member> maybeMember = repository.findByMemberId(loginDto.getMemberId());

        if(maybeMember.equals(Optional.empty())){
            log.info("Id값이 없습니다.");
            return null;
        }

        Member loginMember = maybeMember.get();

        if (!passwordEncoder.matches(loginDto.getMemberPw(), loginMember.getMemberPw())) {
            log.info("Entered wrong password!");
            return null;
        }

        String encodedPassword = passwordEncoder.encode(loginDto.getMemberPw());

        String token = securityService.createToken(loginDto.getMemberId(),loginMember.getMemberNo(), loginMember.getRole(), (30*1000*60));

        return token;
    }

    @Override
    public FindDto findId (FindDto findDto) throws CoolsmsException {
        Optional<Member> findMember = repository.findByMemberName(findDto.getMemberName());

        if(findMember.equals(Optional.empty())) {
            log.info("not exist member");
            return null;
        }

        Member member = findMember.get();

        if(!Objects.equals(member.getMemberPhone(), findDto.getMemberPhone())) {
            log.info("wrong phone number");
            return null;
        }

        findDto.setMemberId(member.getMemberId());
        findDto.setNumStr(phoneCheckService.PhoneNumberCheck(findDto.getMemberPhone()));

        return findDto;
    }

    @Override
    public FindDto findPw (FindDto findDto) throws CoolsmsException {
        Optional<Member> findMember = repository.findByMemberId(findDto.getMemberId());

        if(findMember.equals(Optional.empty())) {
            log.info("not exist member");
            return null;
        }

        Member member = findMember.get();

        if(!Objects.equals(member.getMemberPhone(), findDto.getMemberPhone())) {
            log.info("wrong phone number");
            return null;
        }

        String newPw = RandomPassword.randomPassword(10);
        findDto.setMemberPw(newPw);

        String encodedPassword = passwordEncoder.encode(newPw);
        member.setMemberPw(encodedPassword);
        repository.save(member);

        findDto.setNumStr(phoneCheckService.PhoneNumberCheck(findDto.getMemberPhone()));

        return findDto;
    }

    @Override
    public Member getInfo (String token) {

        String memberId = securityService.getMemberId(token);
        log.info("check >>>>" + memberId);

        Optional<Member> findMember = repository.findByMemberId(memberId);

        Member member = findMember.get();
        member.setMemberPw(null);

        return member;
    }

    @Override
    public Boolean checkPw (LoginDto check) {
        Optional<Member> maybeMember = repository.findByMemberId(check.getMemberId());

        Member loginMember = maybeMember.get();

        if (!passwordEncoder.matches(check.getMemberPw(), loginMember.getMemberPw())) {
            log.info("Entered wrong password!");
            return false;
        }
        return true;
    }

    @Override
    public void modify (MemberDto memberDto) {
        Optional<Member> oMember = repository.findByMemberId(memberDto.getMemberId());

        String encodedPassword = passwordEncoder.encode(memberDto.getMemberPw());
        memberDto.setMemberPw(encodedPassword);

        Member member = oMember.get();
        member.setMemberPw(memberDto.getMemberPw());
        member.setMemberPhone(memberDto.getMemberPhone());
        member.setMemberEmail(memberDto.getMemberEmail());
        member.setAddZipCode(memberDto.getAddZipCode());
        member.setMemberAddress(memberDto.getMemberAddress());
        member.setAddDetail(memberDto.getAddDetail());
        member.setMemberBirth(memberDto.getMemberBirth());

        repository.save(member);

    }

    @Override
    public Boolean remove (Long memberNo, String checkPw) {
        Optional<Member> check = repository.findById(memberNo);

        Member member = check.get();

        if (!passwordEncoder.matches(checkPw, member.getMemberPw())) {
            log.info("Entered wrong password!");
            return false;
        } else {
            repository.deleteById(Long.valueOf(memberNo));
            return true;
        }
    }

    @Override
    public void addMileage(Member member) {
        Optional<Member> optionalMember = repository.findByMemberId(member.getMemberId());

        Member member1 = optionalMember.get();

        member1.setMemberMileage(member.getMemberMileage());

        repository.save(member1);
    }
    

    @Override
    public List<Qna> myQna (Long memberNo) {

        Optional<Member> findMember = repository.findById(memberNo);
        Member member = findMember.get();
//        log.info("Qna()" + member);
        List<Qna> get= qnaRepository.findByMember(member);
//        log.info("Qna()" + get);
        return get;
    }


    @Override
    public List<MyReviewDto> myReview (Long memberNo) {
        Optional<Member> findMember = repository.findById(memberNo);
        Member member = findMember.get();

        List<Review> getReview = reviewRepository.findByReviewWriter(member.getMemberId());
        List<MyReviewDto> reviewDtoList = new ArrayList<>();

        for (int i = 0; i <getReview.size(); i++) {
            Review review = getReview.get(i);
            Performance performance= review.getPerformance();
            MyReviewDto reviewDto = new MyReviewDto();

            reviewDto.setReviewNo(review.getReviewNo());
            reviewDto.setReviewWriter(review.getReviewWriter());
            reviewDto.setReviewContent(review.getReviewContent());
            reviewDto.setReviewRegDate(review.getReviewRegDate());
            reviewDto.setReviewRating(review.getReviewRating());
            reviewDto.setPerformThumbnail(performance.getPerformThumbnail());
            reviewDto.setPerformName(performance.getPerformName());

            reviewDtoList.add(reviewDto);
        }

        return reviewDtoList;
    }

    @Override
    public List<MyTicketDto> myTicket (Long memberNo) {

        Optional<Member> findMember = repository.findById(memberNo);
        Member member = findMember.get();

        List<Ticketing> getTicket = ticketingRepository.findByMemberId(member.getMemberId());
        List<MyTicketDto> ticketDtoList = new ArrayList<>();

        for (int i = 0; i <getTicket.size(); i++) {
            Ticketing ticketing = getTicket.get(i);

            Optional<Performance> getPerform = performanceRepository.findById(ticketing.getPerformNo());
            Performance performance = getPerform.get();

            Optional<Map> getMap = mapRepository.findById(ticketing.getPerformNo());
            Map map = getMap.get();

            MyTicketDto ticketDto = new MyTicketDto();

            ticketDto.setTicketing_no(ticketing.getTicketingNo());
            ticketDto.setFinal_price(ticketing.getFinalPrice());
            ticketDto.setReg_date(ticketing.getReviewRegDate());

            if(ticketing.getUsedCouponNo() != null) {
                Optional<Coupon> getCoupon = couponRepository.findById(ticketing.getUsedCouponNo());
                Coupon coupon = getCoupon.get();
                ticketDto.setUsed_coupon(coupon.getCouponName());
            }

            if(ticketing.getUsedMileage() != null) {
                ticketDto.setUsed_mileage(ticketing.getUsedMileage());
            }

            ticketDto.setSeat(ticketing.getSeatNameArr());
            ticketDto.setPerformShowDate(performance.getPerformShowDate());
            ticketDto.setPerformArea(map.getName());
            ticketDto.setPerformThumbnail(performance.getPerformThumbnail());
            ticketDto.setPerformName(performance.getPerformName());
            ticketDto.setStatus(ticketing.getStatus());

            ticketDtoList.add(ticketDto);
        }

        return ticketDtoList;
    }

    // -----------------------------------------------

    @Override
    public List<Likes> newLikes (Long memberNo) {
        Optional<Member> who = repository.findById(memberNo);
        Member member = who.get();
        return likesRepository.findTop4ByMemberOrderByLikesNoDesc(member);
    }

    @Override
    public List<Qna> newQna (Long memberNo) {
        Optional<Member> who = repository.findById(memberNo);
        Member member = who.get();
        return qnaRepository.findTop5ByMemberOrderByQnaNoDesc(member);
    }

    @Override
    public List<MyTicketDto> newTicket (Long memberNo) {
        Optional<Member> findMember = repository.findById(memberNo);
        Member member = findMember.get();

        List<Ticketing> getTicket = ticketingRepository.findTop5ByMemberIdOrderByTicketingNoDesc(member.getMemberId());
        List<MyTicketDto> ticketDtoList = new ArrayList<>();

        for (int i = 0; i <getTicket.size(); i++) {
            Ticketing ticketing = getTicket.get(i);

            Optional<Performance> getPerform = performanceRepository.findById(ticketing.getPerformNo());
            Performance performance = getPerform.get();

            MyTicketDto ticketDto = new MyTicketDto();

            ticketDto.setTicketing_no(ticketing.getTicketingNo());
            ticketDto.setReg_date(ticketing.getReviewRegDate());
            ticketDto.setSeat(ticketing.getSeatNameArr());
            ticketDto.setPerformShowDate(performance.getPerformShowDate());
            ticketDto.setPerformName(performance.getPerformName());
            ticketDto.setStatus(ticketing.getStatus());

            ticketDtoList.add(ticketDto);
        }

        return  ticketDtoList;
    }
}
