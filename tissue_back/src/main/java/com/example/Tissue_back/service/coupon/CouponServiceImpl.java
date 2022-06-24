package com.example.Tissue_back.service.coupon;

import com.example.Tissue_back.entity.coupon.Coupon;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.repository.coupon.CouponRepository;
import com.example.Tissue_back.repository.member.MemberRepository;
import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MemberRepository memberRepository;

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

    @Override
    public List<Coupon> list() {
        return couponRepository.findAll(Sort.by(Sort.Direction.DESC, "couponNo"));
    }

    @Override
    public Coupon read(Long couponNo) {
        Optional<Coupon> maybeReadCoupon = couponRepository.findById(couponNo);

        if(maybeReadCoupon.equals(Optional.empty())){
            log.info("No coupon");
        }

        return maybeReadCoupon.get();
    }

    @Override
    public void modify(Coupon coupon, @RequestParam(required = false) MultipartFile file) throws Exception {
        Optional<Coupon> selectFile = couponRepository.findById(coupon.getCouponNo());
        Coupon deleteFile = selectFile.get();

        if (deleteFile.getFilename() != null){
            Path filePath = Paths.get("C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\coupon\\" + deleteFile.getFilename());
            Files.delete(filePath);
        }

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


    @Override
    public void remove(Long couponNo) throws Exception {
        Optional<Coupon> selectFile = couponRepository.findById(couponNo);
        Coupon deleteFile = selectFile.get();

        if (deleteFile.getFilename() != null){
            Path filePath = Paths.get("C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\coupon\\" + deleteFile.getFilename());
            Files.delete(filePath);
        }
        couponRepository.deleteById(couponNo);
    }

    @Override
    public void download(Long couponNo, String token) {
        String memberId = securityService.getMemberId(token);
        log.info("check >>>>" + memberId);

        Optional<Member> findMember = memberRepository.findByMemberId(memberId);
        Optional<Coupon> whatCoupon = couponRepository.findById(couponNo);

        Coupon coupon = whatCoupon.get();
        Member member = findMember.get();

        coupon.setMembers(member);

    }

}
