package com.example.Tissue_back.controller.coupon;

import com.example.Tissue_back.entity.coupon.Coupon;
import com.example.Tissue_back.service.coupon.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/coupon")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/register")
    public void couponRegister(@Validated Coupon coupon, @RequestParam(required = false) MultipartFile file) throws Exception {
       log.info("couponRegister()" + file);

       couponService.register(coupon, file);
    }
}
