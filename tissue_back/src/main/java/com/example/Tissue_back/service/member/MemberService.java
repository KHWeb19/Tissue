package com.example.Tissue_back.service.member;

import com.example.Tissue_back.controller.request.member.FindDto;
import com.example.Tissue_back.controller.request.member.LoginDto;
import com.example.Tissue_back.controller.request.member.MemberDto;
import com.example.Tissue_back.controller.request.review.MyReviewDto;
import com.example.Tissue_back.controller.request.ticketing.MyTicketDto;
import com.example.Tissue_back.controller.request.ticketing.TicketingDto;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Likes;
import com.example.Tissue_back.entity.qna.Qna;
import com.example.Tissue_back.entity.review.Review;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import java.util.List;

public interface MemberService {

    public void register(MemberDto memberDto);
    public Boolean checkId(String memberId);
    public String [] login (LoginDto loginDto);
    public FindDto findId(FindDto findDto) throws CoolsmsException;
    public FindDto findPw(FindDto findDto) throws  CoolsmsException;
    public Member getInfo (String token);
    public Boolean checkPw (LoginDto check);
    public void modify (MemberDto memberDto);
    public Boolean remove (Long memberNo, String checkPw);
    public void addMileage(Member member);

    public List<Qna> myQna (Long memberNo);
    public List<MyReviewDto> myReview (Long memberNo);
    public List<MyTicketDto> myTicket (Long memberNo);

    public List<Likes> newLikes (Long memberNo);
    public List<Qna> newQna (Long memberNo);
    public List<MyTicketDto> newTicket (Long memberNo);

}
