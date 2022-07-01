package com.example.Tissue_back.entity.qna;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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
@Table(name = "qnaComment")
public class QnaComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaCommentNo;

    @Lob
    @NotNull
    private String qnaCommentContent;

    @CreatedDate
    private String qnaCommentDate;

    @PrePersist
    public void onPrePersist () {
        this.qnaCommentDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "qnaNo")
    private Qna qna;

}
