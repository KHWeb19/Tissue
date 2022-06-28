package com.example.Tissue_back.controller.coupon;

import com.example.Tissue_back.entity.coupon.Coupon;
import com.example.Tissue_back.service.coupon.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/list")
    public List<Coupon> couponList(){
        log.info("couponList()");

        return couponService.list();
    }

    @GetMapping("/{couponNo}")
    public Coupon couponRead(@PathVariable("couponNo") Long couponNo){
        log.info("couponRead()" + couponNo);

        return couponService.read(couponNo);
    }

    @PutMapping("/{couponNo}")
    public Coupon couponModify(@PathVariable("couponNo")Long couponNo,Coupon coupon,
                               @RequestParam(required = false) MultipartFile file) throws Exception{
        log.info("couponModify()" + couponNo);

        coupon.setCouponNo(couponNo);
        couponService.modify(coupon, file);

        return coupon;
    }


    @DeleteMapping("/{couponNo}")
    public void couponRemove( @PathVariable("couponNo") Long couponNo, Coupon coupon) throws Exception{
        log.info("couponRemove()" + couponNo);

        couponService.remove(couponNo);
    }

    // 쿠폰 다운로드
    @GetMapping("/download/{couponNo}")
    public Boolean couponDown (@PathVariable("couponNo") Long couponNo, @RequestParam(value="token") String token) {
        log.info("couponDown()" + couponNo + token);

        return couponService.download(couponNo, token);

    }

}
