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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PerformanceServiceImpl implements PerformanceService{
    @Autowired
    private PerformanceRepository performanceRepository;

    @Override
    public void register(Performance performance, List<MultipartFile> fileList) throws Exception {
//        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
//
//        UUID uuid = UUID.randomUUID();
//
//        String fileName = uuid + "_" + file.getOriginalFilename();
//
//        File saveFile = new File(filePath, fileName);
//        file.transferTo(saveFile);

        try {
            for (MultipartFile multipartFile : fileList) {
                log.info("requestUploadFile() - Make file: " + multipartFile.getOriginalFilename());

                // 파일 경로 지정
                String filePath = "C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\fileTest";

                UUID uuid = UUID.randomUUID();

                // 파일 원본명 저장
                String fileName = uuid + "_" + multipartFile.getOriginalFilename();

                // 파일 저장
                File saveFile = new File(filePath, fileName);
                multipartFile.transferTo(saveFile);

                // performance db에 저장
                performance.setPerformThumbnail(fileName);
                performance.setPerformThumbnailPath(filePath);

//               파일 저장
//               FileOutputStream saveFile = new FileOutputStream(
//                        "../../tissue_front/src/assets/uploadImg/" + fileName);

//               saveFile.write(multipartFile.getBytes());
//               saveFile.close();
            }
        } catch (Exception e) {

        }

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
    public void modify(Performance performance) {
        performanceRepository.save(performance);
    }

    @Override
    public void remove(Integer performNo) {
        performanceRepository.deleteById(Long.valueOf(performNo));
    }
}
