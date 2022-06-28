package com.example.Tissue_back.service.performance;

import com.example.Tissue_back.entity.performance.Performance;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PerformanceService {
    public Performance register(Performance performance, List<MultipartFile> fileList, MultipartFile file) throws Exception;
    public List<Performance> list();
    public Performance read(Integer boardNo);

    public Performance modify(Performance performance, List<MultipartFile> fileList, MultipartFile file) throws IOException;
    public void remove(Integer performNo);

    public List<Performance> categoryList(String category);
    public List<Performance> search(String keyword);

}
