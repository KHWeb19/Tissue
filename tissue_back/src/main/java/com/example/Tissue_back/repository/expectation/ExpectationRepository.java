package com.example.Tissue_back.repository.expectation;

import com.example.Tissue_back.entity.expectation.Expectation;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExpectationRepository extends JpaRepository<Expectation, Long> {
    List<Expectation> findByEventNo(Long eventNo);

    @Query(value = "select e from Expectation e where e.id = :memberId")
    Optional<Expectation> findByExpectId(String memberId);
}
