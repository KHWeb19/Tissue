package com.example.Tissue_back.service.coupon;

import com.example.Tissue_back.entity.coupon.Coupon;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CouponService {
    public void register(Coupon coupon, @RequestParam(required = false)MultipartFile file) throws IOException,Exception;
    public List<Coupon> list();
    public void remove(Long couponNo) throws Exception;
    public Coupon read(Long couponNo);
    public void modify (Coupon coupon, @RequestParam(required = false)MultipartFile file) throws Exception;
}
