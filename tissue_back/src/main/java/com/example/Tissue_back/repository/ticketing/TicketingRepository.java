package com.example.Tissue_back.repository.ticketing;

import com.example.Tissue_back.entity.ticketing.Ticketing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketingRepository extends JpaRepository<Ticketing, Long> {
}
