package com.example.Tissue_back.repository.map;

import com.example.Tissue_back.entity.map.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MapRepository extends JpaRepository<Map, Long> {
    Optional<Map> findByPerformNo(Long performNo);
}
