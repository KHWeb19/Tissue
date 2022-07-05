package com.example.Tissue_back.repository.performance;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.performance.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Query("select p from Performance p where p.performNo = :performNo")
    Optional<Performance> findByPerformNo(@Param("performNo") Long performNo);

    @Transactional
    @Query(value = "select p from Performance p where p.performCategory = :category")
    List<Performance> findPerformanceWithCategory(@Param("category") String category);

    List<Performance> findByPerformNameContaining (String keyword);

    Performance findByPerformName (String performName);
}
