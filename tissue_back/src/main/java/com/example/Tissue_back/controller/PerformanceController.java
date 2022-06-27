package com.example.Tissue_back.controller;

import com.example.Tissue_back.controller.request.PerformanceDto;
import com.example.Tissue_back.entity.Performance;
import com.example.Tissue_back.repository.PerformanceRepository;
import com.example.Tissue_back.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/performance")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private PerformanceRepository performanceRepository;

    @CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
    @PostMapping(value= "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity performanceRegister(@RequestPart("performance") Performance performance,
                                              @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList,
                                              @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {
        log.info("performanceRegister()" + performance);
        log.info("file()" + file);
        log.info("fileList()" + fileList);

        Performance performance1 = performanceService.register(performance, fileList, file);

        return new ResponseEntity(performance1, HttpStatus.OK );
    }

    @GetMapping("/list")
    public List<Performance> performanceList() {
        log.info("performanceList()");

        return performanceService.list();
    }

    @GetMapping("/{performNo}")
    public Performance performanceRead(@PathVariable("performNo") Integer performNo) {
        log.info("performanceRead()");

        return performanceService.read(performNo);
    }

    @PutMapping(value="/{performNo}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Performance performanceModify (
            @PathVariable("performNo") Integer performNo,
            @RequestPart("performance") Performance performance,
            @RequestParam(value = "fileList", required = false ) List<MultipartFile> fileList,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        log.info ("performanceModify(): " + performance);

        Optional<Performance> maybeFile = performanceRepository.findById(Long.valueOf(performNo));
        Performance findFile = maybeFile.get();

        // 다중파일 기존 경로 삭제
        if(findFile.getPerformDetailImg1() != null) {
            Path filePath = Paths.get("c:\\khweb19\\Tissue\\tissue_front\\src\\assets\\detailImg\\" + findFile.getPerformDetailImg1());
            Files.delete(filePath);
        }
        if(findFile.getPerformDetailImg2() != null) {
            Path filePath = Paths.get("c:\\khweb19\\Tissue\\tissue_front\\src\\assets\\detailImg\\" + findFile.getPerformDetailImg2());
            Files.delete(filePath);
        }
        if(findFile.getPerformDetailImg3() != null) {
            Path filePath = Paths.get("c:\\khweb19\\Tissue\\tissue_front\\src\\assets\\detailImg\\" + findFile.getPerformDetailImg3());
            Files.delete(filePath);
        }
        if(findFile.getPerformDetailImg4() != null) {
            Path filePath = Paths.get("c:\\khweb19\\Tissue\\tissue_front\\src\\assets\\detailImg\\" + findFile.getPerformDetailImg4());
            Files.delete(filePath);
        }
        if(findFile.getPerformDetailImg5() != null) {
            Path filePath = Paths.get("c:\\khweb19\\Tissue\\tissue_front\\src\\assets\\detailImg\\" + findFile.getPerformDetailImg5());
            Files.delete(filePath);
        }

        // 썸네일 기존경로 삭제
        if(findFile.getPerformThumbnail() != null) {
            Path filePath = Paths.get("c:\\khweb19\\Tissue\\tissue_front\\src\\assets\\thumbNail\\" + findFile.getPerformThumbnail());
            Files.delete(filePath);
        }


        performance.setPerformNo((Long.valueOf(performNo)));
        Performance performance1 = performanceService.modify(performance, fileList, file);

        return performance1;
    }

    @DeleteMapping("/{performNo}")
    public void performanceRemove (
            @PathVariable("performNo") Integer performNo) {
        log.info("performanceRemove()");

        performanceService.remove(performNo);
    }
}
