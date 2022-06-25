package com.example.Tissue_back.entity.qna;

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
@Table(name = "qnaBest")
public class QnaBest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaBestNo;

    @Column
    @NotNull
    private String qnaBestCategory;

    @Column
    @NotNull
    private String qnaBestTitle;

    @Lob
    @NotNull
    private String qnaBestContent;

    /*
    @Column(columnDefinition = "integer default 0")
    @NotNull
    private int qnaBestView;

    @CreatedDate
    private String qnaBestDate;

    @PrePersist
    public void onPrePersist () {
        this.qnaBestDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }*/

}
