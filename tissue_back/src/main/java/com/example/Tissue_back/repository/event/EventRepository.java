package com.example.Tissue_back.repository.event;

import com.example.Tissue_back.entity.event.Event;
import com.example.Tissue_back.entity.map.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Transactional
    @Query("select e from Event e join e.performance p where p.performNo = :performNo")
    Event findEventWithPerformNo(@Param("performNo")Long performNo);
}
