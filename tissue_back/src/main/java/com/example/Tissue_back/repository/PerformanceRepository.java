package com.example.Tissue_back.repository;

import com.example.Tissue_back.entity.Performance;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Transactional
    @Query(value = "select p from Performance p where p.performCategory = :category")
    List<Performance> findPerformanceWithCategory(@Param("category") String category);


}
