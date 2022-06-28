package com.example.Tissue_back.controller;

import com.example.Tissue_back.controller.request.performance.KeywordDto;
import com.example.Tissue_back.entity.Performance;
import com.example.Tissue_back.entity.notice.Notice;
import com.example.Tissue_back.service.notice.NoticeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice")
@CrossOrigin(origins="http://localhost:8080", allowedHeaders = "*")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/register")
    public void NoticeRegister (@Validated Notice notice, @RequestParam(required = false) MultipartFile file) throws Exception {
        log.info("Notice Register: " + notice + "file: " +file);

        noticeService.register(notice, file);
    }

    @GetMapping("/list")
    public List<Notice> NoticeList() {
        log.info("Notice List");

        return noticeService.list();
    }

    @GetMapping("/{noticeNo}")
    public Notice NoticeRead (@PathVariable("noticeNo") Long noticeNo){
        log.info("Notice Read");

        noticeService.updateNoticeView(noticeNo);
        return noticeService.read(noticeNo);
    }


    @PutMapping("/{noticeNo}")
    public Notice NoticeModify (@PathVariable("noticeNo") Long noticeNo, Notice notice,
                                @RequestParam(required = false) MultipartFile file) throws Exception {
        log.info("Notice Modify");

        noticeService.modify(notice, file);

        return notice;
    }

    @DeleteMapping("/delete/{noticeNo}")
    public void NoticeDelete (@PathVariable("noticeNo") Long noticeNo, Notice notice) throws Exception{
        log.info("Notice Delete");

        noticeService.remove(noticeNo);
    }

    // 검색 (유아림)
    @PostMapping("/search")
    public List<Notice> searchList (@RequestBody KeywordDto keywordDto) {
        log.info("=== search ===" + keywordDto);
        String keyword = keywordDto.getKeyword();
        return noticeService.search(keyword);
    }


}
