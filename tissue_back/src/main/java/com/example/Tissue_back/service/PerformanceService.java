package com.example.Tissue_back.service;

import com.example.Tissue_back.entity.Performance;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PerformanceService {
    public void register(Performance performance, List<MultipartFile> fileList, MultipartFile file) throws Exception;
    public List<Performance> list();
    public Performance read(Integer boardNo);
    public void modify(Performance performance);
    public void remove(Integer performNo);
}
