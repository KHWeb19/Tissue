package com.example.Tissue_back.repository.ticketing;

import com.example.Tissue_back.entity.ticketing.RefundRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<RefundRequest, Long> {


}
