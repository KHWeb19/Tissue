package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.PerformanceDto;
import com.example.Tissue_back.entity.Performance;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PerformanceService {
    public Performance register(Performance performance, List<MultipartFile> fileList, MultipartFile file) throws Exception;
    public List<Performance> list();
    public Performance read(Integer boardNo);

    public Performance modify(Performance performance, List<MultipartFile> fileList, MultipartFile file) throws IOException;
    public void remove(Integer performNo);


}
