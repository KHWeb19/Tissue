package com.example.Tissue_back.service.review;

import com.example.Tissue_back.controller.request.review.ReviewDto;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.entity.review.Review;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.repository.review.ReviewRepository;
import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public void register(Long performNo, ReviewDto reviewDto) {
        Optional<Performance> findPerformance = performanceRepository.findById(performNo);
        String memberId = securityService.getMemberId(reviewDto.getReviewWriter());

        Review review = new Review();

        reviewDto.setReviewWriter(memberId);
        reviewDto.setPerformance(findPerformance.get());

        review.setReviewWriter(reviewDto.getReviewWriter());
        review.setReviewContent(reviewDto.getReviewContent());
        review.setReviewRating(reviewDto.getReviewRating());
        review.setPerformance(reviewDto.getPerformance());




        reviewRepository.save(review);
    }

    @Override
    public List<Review> list(Long performNo) {
        Performance performance = performanceRepository.findById(performNo).get();

        return reviewRepository.findReviewByPerformance(performance);
    }
}
