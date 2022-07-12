package com.example.Tissue_back.entity.notice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
@Entity
@NoArgsConstructor
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeNo;

    @Column(nullable = false)
    private String noticeCategory;

    @Column(nullable = false)
    private String noticeTitle;

    @Column
    private String noticeContent;

    @CreatedDate
    private String noticeDate;

    @PrePersist
    public void onPrePersist () {
        this.noticeDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    @Column(nullable = false)
    private String noticeNecessary;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int noticeView;

    @Column
    private String noticeImg;

    @Column
    private String noticeYoutube;
}
