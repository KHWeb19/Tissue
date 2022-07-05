package com.example.Tissue_back.repository;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Likes;
import com.example.Tissue_back.entity.performance.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByMemberAndPerformance (Member member, Performance performance);

    List<Likes> findByPerformance (Performance performance);
    List<Likes> findByMember (Member member);
}
