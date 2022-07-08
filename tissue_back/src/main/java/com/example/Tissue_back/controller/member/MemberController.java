package com.example.Tissue_back.controller.member;

import com.example.Tissue_back.controller.request.member.FindDto;
import com.example.Tissue_back.controller.request.member.LoginDto;
import com.example.Tissue_back.controller.request.member.MemberDto;
import com.example.Tissue_back.controller.request.review.MyReviewDto;
import com.example.Tissue_back.controller.request.ticketing.MyTicketDto;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Likes;
import com.example.Tissue_back.entity.qna.Qna;
import com.example.Tissue_back.entity.review.Review;
import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/Member")
@CrossOrigin(origins="http://localhost:8080", allowedHeaders = "*")
public class MemberController {

    @Autowired
    private MemberService service;

    // 회원가입
    @PostMapping("/register")
    public MemberDto register (@Validated @RequestBody MemberDto memberDto) {
        log.info("== Tissue Member Register ==" + memberDto);

        service.register(memberDto);

        return memberDto;
    }

    // 아이디 중복 여부 체크
    @GetMapping("/checkId/{memberId}")
    public Boolean checkId (@PathVariable("memberId") String memberId){
        log.info("checkId(): " + memberId);

        Boolean checkId = service.checkId(memberId);

        return checkId;
    }

    // 로그인
    @PostMapping("/login")
    public String login (@Validated @RequestBody LoginDto loginDto) throws Exception{

        log.info("== Tissue Member Login ==" + loginDto);

        String response = service.login(loginDto);
        log.info("check" + response);

        if (response != null) {
            log.info("로그인 성공!");
        } else {
            log.info("로그인 실패!");
        }
        return response;
    }

    // 아이디 찾기
    @PostMapping("/findId")
    public FindDto findId (@Validated @RequestBody FindDto findDto) throws CoolsmsException {
        log.info("== find Id ==" + findDto);

        return service.findId(findDto);
    }

    // 비밀번호 찾기
    @PostMapping("/findPw")
    public FindDto findPw (@Validated @RequestBody FindDto findDto) throws CoolsmsException {
        log.info("== find Pw ==" + findDto);

        return service.findPw(findDto);
    }

    // 회원정보 가져오기
    @GetMapping("/info")
    public Member getInfo (@RequestParam(value = "token") String token) {
        log.info ("== Get Info ==" + token);

        return service.getInfo(token);
    }

    @PostMapping("/checkPw")
    public Boolean checkPw (@Validated @RequestBody LoginDto check) {
        log.info("==Check Pw==" + check);
        return service.checkPw(check);
    }

    // 회원정보 수정
    @PutMapping("/modify")
    public MemberDto modify (@Validated @RequestBody MemberDto memberDto) {
        log.info("=== Tissue Member Modify ===" + memberDto);

        service.modify(memberDto);

        return memberDto;
    }

    // 회원탈퇴
    @DeleteMapping("/remove/{memberNo}")
    public Boolean remove (@PathVariable("memberNo") Long memberNo,
                           @RequestParam(value = "checkPw") String checkPw) {
        log.info("remove()" + memberNo + checkPw);

        return service.remove(memberNo, checkPw);
    }

    @PostMapping("/addMileage")
    public void addMileage (@RequestBody Member member) {
        log.info("add mileage()");

        service.addMileage(member);

    }
    
    @GetMapping("/myQna/{memberNo}")
    public List<Qna> myQna (@PathVariable("memberNo") Long memberNo) {
        log.info("my QnA List ===");

        return service.myQna(memberNo);
    }
    

    @GetMapping("/myReview/{memberNo}")
    public List<MyReviewDto> myReview (@PathVariable("memberNo") Long memberNo) {
        log.info("== my Review List ==");

        return service.myReview(memberNo);
    }

    @GetMapping("/myTicket/{memberNo}")
    public List<MyTicketDto> myTicket (@PathVariable("memberNo") Long memberNo) {
        log.info("==my Ticket List ==");

        return service.myTicket(memberNo);
    }

    // 최신 목록
    @GetMapping("/newLikes/{memberNo}")
    public List<Likes> newLikes (@PathVariable("memberNo") Long memberNo) {
        log.info("get New Likes");

        return service.newLikes(memberNo);
    }
    @GetMapping("/newQna/{memberNo}")
    public List<Qna> newQna (@PathVariable("memberNo") Long memberNo) {
        log.info("get New Qna");

        return service.newQna(memberNo);
    }
    @GetMapping("/newTicket/{memberNo}")
    public List<MyTicketDto> newTicket (@PathVariable("memberNo") Long memberNo) {
        log.info("get New TIcket");

        return service.newTicket(memberNo);
    }
}
