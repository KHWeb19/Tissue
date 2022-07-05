package com.example.Tissue_back.entity.qna;

import com.example.Tissue_back.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "qna")
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaNo;

    @Column
    @NotNull
    private String qnaCategory;

    @Column
    @NotNull
    private String qnaTitle;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String qnaContent;

    @Column
    @NotNull
    private String qnaWriter;
    //공개글?비공개글?
    @Column
    @NotNull
    private Boolean qnaSecret;

    @Column
    @NotNull
    private String qnaPw;

    //답변여부
    @Column
    @NotNull
    private Boolean qnaCheck;

    @CreatedDate
    private String qnaDate;

    @PrePersist
    public void onPrePersist () {
        this.qnaDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    @OneToMany(mappedBy = "qna", fetch = FetchType.EAGER)
    private List<QnaComment> commentList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "memberNo")
    private Member member;
}
