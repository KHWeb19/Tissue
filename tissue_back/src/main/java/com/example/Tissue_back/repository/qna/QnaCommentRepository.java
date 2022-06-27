package com.example.Tissue_back.repository.qna;

import com.example.Tissue_back.entity.qna.Qna;
import com.example.Tissue_back.entity.qna.QnaComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaCommentRepository extends JpaRepository <QnaComment, Long> {
    List<QnaComment> findCommentByQna(Qna qna);
}
