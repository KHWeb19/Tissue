package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.LikesDto;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Likes;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.repository.LikesRepository;
import com.example.Tissue_back.repository.member.MemberRepository;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    LikesRepository likeRepository;
    @Autowired
    PerformanceRepository performanceRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    SecurityService securityService;

    @Override
    public List<Likes> iLike (LikesDto likesDto) {
        log.info("like service()");

        Optional<Performance> getPerform = performanceRepository.findById(likesDto.getPerformNo());
        Performance performance = getPerform.get();

        String memberId = securityService.getMemberId(likesDto.getToken());
        log.info("memberId" + memberId);

        Optional<Member> getMember= memberRepository.findByMemberId(memberId);
        Member member = getMember.get();

        log.info("next");

        if (likeRepository.findByMemberAndPerformance(member,performance).isEmpty()) {

            Likes likes = new Likes();
            likes.setMember(member);
            likes.setPerformance(performance);
            likeRepository.save(likes);

        }
        return likeRepository.findByPerformance(performance);
    }

    @Override
    public List<Likes> iDislike (String token, Long performNo) {
        log.info("Dislike");

        Optional<Performance> getPerform = performanceRepository.findById(performNo);
        Performance performance = getPerform.get();

        String memberId = securityService.getMemberId(token);
        log.info("memberId" + memberId);

        Optional<Member> getMember= memberRepository.findByMemberId(memberId);
        Member member = getMember.get();

        Optional<Likes> maybeLikes = likeRepository.findByMemberAndPerformance(member, performance);
        log.info("check  success ! ! ");
        likeRepository.deleteById(maybeLikes.get().getLikesNo());

        return likeRepository.findByPerformance(performance);

    }

    @Override
    public List<Likes> howLikes (Long performNo){
        log.info("how many likes");
        Optional<Performance> performance = performanceRepository.findById(performNo);
        Performance getPerform = performance.get();

        return likeRepository.findByPerformance(getPerform);

    }

    @Override
    public List<Likes> myLikes (Long memberNo) {
        log.info("myLikes()");
        Optional<Member> getMember= memberRepository.findById(memberNo);

        Member member = getMember.get();

        return likeRepository.findByMember(member);
    }

}
