package com.example.Tissue_back.repository;

import com.example.Tissue_back.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Transactional
    @Modifying
    @Query("update Notice n set n.noticeView = n.noticeView + 1 where n.noticeNo = :noticeNo")
    int updateNoticeView(Long noticeNo);
}
