package com.example.Tissue_back.service.review;

import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.entity.review.Review;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.repository.review.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Override
    public void register(Review review, Long performNo) {
        Optional<Performance> findPerformance = performanceRepository.findById(performNo);

        review.setPerformance(findPerformance.get());

        reviewRepository.save(review);
    }
}
