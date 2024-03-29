package com.example.Tissue_back.service.qna;

import com.example.Tissue_back.entity.qna.QnaComment;

import java.util.List;

public interface QnaCommentService {
    public void register(QnaComment qnaComment, Long qnaNo);
    public List<QnaComment> list (Long qnaNo);
    //public QnaComment read (Qna)
    public void modify (QnaComment qnaComment, Long qnaNo);
    public void delete (Long qnaNo);

}
