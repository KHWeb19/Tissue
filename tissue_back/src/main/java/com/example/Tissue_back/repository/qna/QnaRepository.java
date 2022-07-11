package com.example.Tissue_back.repository.qna;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Likes;
import com.example.Tissue_back.entity.qna.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    List<Qna> findByMember (Member member);
    List<Qna> findTop5ByMemberOrderByQnaNoDesc(Member member);
}
