package com.example.Tissue_back.repository.expectation;

import com.example.Tissue_back.entity.expectation.Expectation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpectationRepository extends JpaRepository<Expectation, Long> {
    List<Expectation> findByEventNo(Long eventNo);
}
