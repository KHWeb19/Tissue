package com.example.Tissue_back.service.coupon;

import com.example.Tissue_back.entity.coupon.Coupon;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CouponService {
    public void register(Coupon coupon, @RequestParam(required = false)MultipartFile file) throws IOException,Exception;
}
