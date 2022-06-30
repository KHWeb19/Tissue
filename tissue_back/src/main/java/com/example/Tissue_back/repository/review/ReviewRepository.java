package com.example.Tissue_back.repository.review;

import com.example.Tissue_back.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
