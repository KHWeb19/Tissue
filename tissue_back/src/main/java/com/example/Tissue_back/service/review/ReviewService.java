package com.example.Tissue_back.service.review;

import com.example.Tissue_back.controller.request.review.ReviewDto;
import com.example.Tissue_back.entity.review.Review;
import java.util.List;

public interface ReviewService {
    public void register(Long performNo, ReviewDto reviewDto);
    public List<Review> list(Long performNo);
    public void modify (Review review, String performName);
    public void delete (Long reviewNo);
}
