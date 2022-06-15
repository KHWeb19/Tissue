package com.example.Tissue_back.service.Notice;

import com.example.Tissue_back.entity.Notice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoticeService {
    public void register (Notice notice, @RequestParam(required = false) MultipartFile file) throws Exception;
    public List<Notice> list();
    public Notice read (Long noticeNo);
    public void modify (Notice notice, @RequestParam(required = false) MultipartFile file) throws Exception;
    public void remove (Long noticeNo);
    //조회수
    public int updateNoticeView(Long noticeNo);
}
