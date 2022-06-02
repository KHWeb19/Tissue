package com.example.Tissue_back.controller;

import com.example.Tissue_back.entity.Performance;
import com.example.Tissue_back.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/performance")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/register")
    public void performanceRegister(@Validated @RequestBody Performance performance, @RequestParam("fileList") List<MultipartFile> fileList) throws Exception {
        log.info("performanceRegister()");

        performanceService.register(performance, (List<MultipartFile>) fileList);
    }

    @GetMapping("/list")
    public List<Performance> performanceList() {
        log.info("performanceList()");

        return performanceService.list();
    }

    @GetMapping("/{performNo}")
    public Performance performanceRead(@PathVariable("performNo") Integer performNo) {
        log.info("noticeBoardRead");

        return performanceService.read(performNo);
    }

    @PutMapping("/{performNo}")
    public Performance performanceModify (
            @PathVariable("performNo") Integer performNo,
            @RequestBody Performance performance) {
        log.info ("performanceModify(): " + performance);

        performance.setPerformNo((Long.valueOf(performNo)));
        performanceService.modify(performance);

        return performance;
    }

    @DeleteMapping("/{performNo}")
    public void performanceRemove (
            @PathVariable("performNo") Integer performNo) {
        log.info("performanceRemove()");

        performanceService.remove(performNo);
    }
}
