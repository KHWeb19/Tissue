package com.example.Tissue_back.repository.ticketing;

import com.example.Tissue_back.entity.ticketing.TicketingSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface TicketingSeatRepository extends JpaRepository<TicketingSeat, Long> {

    @Query("select ts from TicketingSeat ts where ts.performNo = :performNo")
    List<TicketingSeat>findByPerformNo(@Param("performNo") Long performNo);
}
