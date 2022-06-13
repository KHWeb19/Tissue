package com.example.Tissue_back.service;

import com.example.Tissue_back.entity.Performance;
import com.example.Tissue_back.repository.PerformanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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
public class PerformanceServiceImpl implements PerformanceService{
    @Autowired
    private PerformanceRepository performanceRepository;

    @Override
    public void register(Performance performance, List<MultipartFile> fileList, MultipartFile file) throws Exception {

        // 다중이미지 등록
        try {
            for (MultipartFile multipartFile : fileList) {
                log.info("requestUploadFile() - Make file: " + multipartFile.getOriginalFilename());

                // 파일 경로 지정
                String filePath = "C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\detailImg";

                UUID uuid = UUID.randomUUID();

                // 파일 원본명 저장
                String fileName = uuid + "_" + multipartFile.getOriginalFilename();

                // 파일 저장
                File saveFile = new File(filePath, fileName);
                multipartFile.transferTo(saveFile);

                // performance db에 detailImg 저장
                if(multipartFile == fileList.get(0)) {
                    performance.setPerformDetailImg1(fileName);
                    performance.setPerformDetailImgPath(filePath);
                } else if (multipartFile == fileList.get(1)) {
                    performance.setPerformDetailImg2(fileName);
                    performance.setPerformDetailImgPath(filePath);
                } else if (multipartFile == fileList.get(2)) {
                    performance.setPerformDetailImg3(fileName);
                    performance.setPerformDetailImgPath(filePath);
                } else if (multipartFile == fileList.get(3)) {
                    performance.setPerformDetailImg4(fileName);
                    performance.setPerformDetailImgPath(filePath);
                } else {
                    performance.setPerformDetailImg5(fileName);
                    performance.setPerformDetailImgPath(filePath);
                }

//               파일 저장
//               FileOutputStream saveFile = new FileOutputStream(
//                        "../../tissue_front/src/assets/uploadImg/" + fileName);

//               saveFile.write(multipartFile.getBytes());
//               saveFile.close();
            }
        } catch (Exception e) {

        }

        // 썸네일 등록
        String thumbNailPath = "C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\thumbNail";

        UUID uuid = UUID.randomUUID();

        String thumbNailFileName = uuid + "_" + file.getOriginalFilename();

        File saveThumbNail = new File(thumbNailPath, thumbNailFileName);
        file.transferTo(saveThumbNail);

        performance.setPerformThumbnail(thumbNailFileName);
        performance.setPerformThumbnailPath(thumbNailPath);

        log.info("requestUploadFile(): Success");

        performanceRepository.save(performance);
    }



    @Override
    public List<Performance> list() {
        return performanceRepository.findAll(Sort.by(Sort.Direction.DESC, "performNo"));
    }

    @Override
    public Performance read(Integer performNo) {
        Optional<Performance> maybePerformance = performanceRepository.findById(Long.valueOf(performNo));

        if(maybePerformance.equals(Optional.empty())) {
            log.info("Cannot read performance list");
            return null;
        }
        return maybePerformance.get();
    }

    @Override
    public void modify(Performance performance, List<MultipartFile> fileList, MultipartFile file) throws IOException {
        // 다중이미지 수정

        try {
            for (MultipartFile multipartFile : fileList) {
                if (multipartFile != null) {
                    String filePath = "C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\detailImg";

                    UUID uuid = UUID.randomUUID();

                    // 파일 원본명 저장
                    String fileName = uuid + "_" + multipartFile.getOriginalFilename();

                    // 파일 저장
                    File saveFile = new File(filePath, fileName);
                    multipartFile.transferTo(saveFile);

                    // performance db에 detailImg 저장
                    if(multipartFile == fileList.get(0)) {
                        performance.setPerformDetailImg1(fileName);
                        performance.setPerformDetailImgPath(filePath);
                    } else if (multipartFile == fileList.get(1)) {
                        performance.setPerformDetailImg2(fileName);
                        performance.setPerformDetailImgPath(filePath);
                    } else if (multipartFile == fileList.get(2)) {
                        performance.setPerformDetailImg3(fileName);
                        performance.setPerformDetailImgPath(filePath);
                    } else if (multipartFile == fileList.get(3)) {
                        performance.setPerformDetailImg4(fileName);
                        performance.setPerformDetailImgPath(filePath);
                    } else {
                        performance.setPerformDetailImg5(fileName);
                        performance.setPerformDetailImgPath(filePath);
                    }
                }
            }
        }catch (Exception e) {

        }

         //썸네일 수정
        if (file != null) {
            String thumbNailPath = "C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\thumbNail";

            UUID uuid = UUID.randomUUID();

            String thumbNailFileName = uuid + "_" + file.getOriginalFilename();

            File saveThumbNail = new File(thumbNailPath, thumbNailFileName);
            file.transferTo(saveThumbNail);

            performance.setPerformThumbnail(thumbNailFileName);
            performance.setPerformThumbnailPath(thumbNailPath);
        }

        performanceRepository.save(performance);
    }

    @Override
    public void remove(Integer performNo) {
        performanceRepository.deleteById(Long.valueOf(performNo));
    }
}
