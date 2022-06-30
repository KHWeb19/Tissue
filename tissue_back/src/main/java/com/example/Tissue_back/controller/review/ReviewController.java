package com.example.Tissue_back.controller.review;


import com.example.Tissue_back.entity.review.Review;
import com.example.Tissue_back.service.review.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("register/{performNo}")
    public void reviewRegister(@Validated @RequestBody Review review, @PathVariable("performNo")Long performNo){
        log.info("reviewRegister()" + performNo);

        reviewService.register(review,performNo);
    }
}
