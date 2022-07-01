package com.example.Tissue_back.controller.review;


import com.example.Tissue_back.controller.request.review.ReviewDto;
import com.example.Tissue_back.entity.review.Review;
import com.example.Tissue_back.service.review.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("register/{performNo}")
    public void reviewRegister(@Validated @RequestBody ReviewDto reviewDto, @PathVariable("performNo")Long performNo){
        log.info("reviewRegister()" + performNo + reviewDto);

        reviewService.register(performNo, reviewDto);
    }

    @GetMapping("list/{performNo}")
    public List<Review> reviewList(@PathVariable("performNo")Long performNo){
        log.info("reviewList()" + performNo);

        return reviewService.list(performNo);
    }
}
