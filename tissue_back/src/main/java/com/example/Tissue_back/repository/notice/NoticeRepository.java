package com.example.Tissue_back.repository.notice;

import com.example.Tissue_back.entity.Performance;
import com.example.Tissue_back.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Transactional
    @Modifying
    @Query("update Notice n set n.noticeView = n.noticeView + 1 where n.noticeNo = :noticeNo")
    int updateNoticeView(Long noticeNo);

    List<Notice> findByNoticeTitleContaining (String keyword);
}
