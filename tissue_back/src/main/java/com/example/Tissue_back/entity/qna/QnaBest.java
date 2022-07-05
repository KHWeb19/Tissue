package com.example.Tissue_back.entity.qna;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String qnaBestContent;

}
