package com.example.Tissue_back.repository;

import com.example.Tissue_back.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    @Transactional
    @Query(value = "select p from Performance p where p.performCategory = '콘서트'")
    List<Performance> findPerformanceWithConcert();

    @Transactional
    @Query(value = "select p from Performance p where p.performCategory = '뮤지컬'")
    List<Performance> findPerformanceWithMusical();

    @Transactional
    @Query(value = "select p from Performance p where p.performCategory = '연극'")
    List<Performance> findPerformanceWithTheater();

    @Transactional
    @Query(value = "select p from Performance p where p.performCategory = '전시회'")
    List<Performance> findPerformanceWithExhibition();
}
