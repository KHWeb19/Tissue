package com.example.Tissue_back.service.coupon;

import com.example.Tissue_back.entity.coupon.Coupon;
import com.example.Tissue_back.repository.coupon.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void register(Coupon coupon, MultipartFile file) throws Exception {
        if (file != null) {
            String filepath = "C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\coupon";

            UUID uuid = UUID.randomUUID();

            String fileName = uuid + "_" + file.getOriginalFilename();

            File saveCoupon = new File(filepath,fileName);
            file.transferTo(saveCoupon);



            coupon.setFilename(fileName);
            coupon.setFilepath(fileName);
        }

        couponRepository.save(coupon);
    }
}
