package com.example.Tissue_back.service.qna;

import com.example.Tissue_back.entity.qna.Qna;
import com.example.Tissue_back.entity.qna.QnaComment;
import com.example.Tissue_back.repository.qna.QnaCommentRepository;
import com.example.Tissue_back.repository.qna.QnaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnaCommentServiceImpl implements QnaCommentService {

    @Autowired
    QnaCommentRepository commentRepository;

    @Autowired
    QnaRepository qnaRepository;

    @Override
    public void register(QnaComment qnaComment, Long qnaNo){
        Optional<Qna> findQna = qnaRepository.findById(qnaNo);

        qnaComment.setQna(findQna.get());
        commentRepository.save(qnaComment);

        //댓글 등록시 자동으로 QNA Check : false -> true로 변경
        log.info("Enter QnaRepository");
        Qna qna = findQna.get();
        log.info("QnaCheck = true");
        qna.setQnaCheck(true);

        qnaRepository.save(qna);
    }

    @Override
    public List<QnaComment> list (Long qnaNo){
        Qna qna = qnaRepository.findById(qnaNo).get();

        return commentRepository.findCommentByQna(qna);
    }

    @Override
    public void delete (Long qnaCommentNo) {
        commentRepository.deleteById(qnaCommentNo);
    }

}
