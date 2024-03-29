package com.example.Tissue_back.service.qna;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.qna.Qna;
import com.example.Tissue_back.repository.member.MemberRepository;
import com.example.Tissue_back.repository.qna.QnaRepository;
import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.regex.Pattern.matches;

@Slf4j
@Service
public class QnaServiceImpl implements QnaService{

    @Autowired
    private QnaRepository repository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService securityService;


    @Override
    public void register (Qna qna, Long memberNo) {
        /*String memberId = securityService.getMemberId(token);

        Optional<Member> findMember = memberRepository.findByMemberId(memberId);

        qna.setMember(findMember.get());

        repository.save(qna);*/

        Optional<Member> findMember = memberRepository.findById(memberNo);
        qna.setMember(findMember.get());
        repository.save(qna);
    }

    @Override
    public List<Qna> list() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "qnaNo"));
    }

    @Override
    public Qna read (Long qnaNo){

        Optional<Qna> maybeReadQna = repository.findById(qnaNo);


        if(maybeReadQna.equals(Optional.empty())) {
            log.info("Can't read board");
            return null;
        }
        return maybeReadQna.get();
    }

    @Override
    public void modify (Qna qna, Long memberNo) {

        Optional<Member> findMember = memberRepository.findById(memberNo);
        qna.setMember(findMember.get());
        repository.save(qna);
    }

    @Override
    public void checkModifyTrue (Qna qna) {
        repository.save(qna);
    }

    @Override
    public void remove(Long qnaNo) {
        repository.deleteById(qnaNo);
    }

    // 비밀글 확인
    @Override
    public Boolean checkSecret (Long qnaNo) {
        Optional<Qna> checkQnaNo = repository.findById(qnaNo);

        if(checkQnaNo.get().getQnaSecret()) {
            log.info("Secret == True");
            return true;
        } else {
            log.info("Secret == False");
            return false;
        }
    }
    //비밀번호 일치 여부 확인
    @Override
    public Boolean checkQnaPw (Integer qnaNo, String qnaPw) {
        Optional<Qna> maybeQna = repository.findById(Long.valueOf(qnaNo));

        if(maybeQna.get().getQnaPw().matches(qnaPw)) {
            log.info("Password Correct!");
            return true;
        } else {
            log.info("Password Wrong!");
            return false;
        }

    }
}
