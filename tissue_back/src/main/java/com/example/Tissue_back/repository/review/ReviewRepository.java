package com.example.Tissue_back.repository.review;

import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findReviewByPerformance(Performance performance);
    List<Review> findByReviewWriter(String memberId);
}
