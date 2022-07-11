package com.example.Tissue_back.repository.ticketing;

import com.example.Tissue_back.entity.ticketing.Ticketing;
import com.example.Tissue_back.entity.ticketing.Ticketing2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Ticketing2Repository extends JpaRepository <Ticketing2, Long> {

    @Query("select t from Ticketing2 t where t.seatNameArr = :seatNameArr")
    Optional<Ticketing2> findBySeat(@Param("seatNameArr") String [] seatNameArr);
}
