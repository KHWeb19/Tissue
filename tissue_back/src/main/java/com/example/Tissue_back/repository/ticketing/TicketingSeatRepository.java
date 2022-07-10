package com.example.Tissue_back.repository.ticketing;

import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.entity.ticketing.Ticketing2;
import com.example.Tissue_back.entity.ticketing.TicketingSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.*;

public interface TicketingSeatRepository extends JpaRepository<TicketingSeat, Long> {

    @Query("select ts from TicketingSeat ts where ts.performNo = :performNo")
    List<TicketingSeat>findByPerformNo(@Param("performNo") Long performNo);

    @Transactional
    @Modifying
    @Query("delete from TicketingSeat ts where ts.ticketing = :ticketing")
    void deleteByTicketingNo(@Param("ticketing") Ticketing ticketing);

    @Transactional
    @Modifying
    @Query("delete from TicketingSeat ts where ts.ticketing2 = :ticketing2")
    void deleteNonMemberByTicketingNo(@Param("ticketing2")Ticketing2 ticketing2);
}
