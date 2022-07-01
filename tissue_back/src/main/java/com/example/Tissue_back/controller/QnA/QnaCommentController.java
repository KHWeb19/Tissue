package com.example.Tissue_back.controller.QnA;

import com.example.Tissue_back.entity.qna.QnaComment;
import com.example.Tissue_back.service.qna.QnaCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qnaComment")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QnaCommentController {

    @Autowired
    private QnaCommentService service;

    @PostMapping("/{qnaNo}")
    public void CommentRegister (@Validated @RequestBody QnaComment qnaComment,
                                 @PathVariable("qnaNo") Long qnaNo) {

        log.info("CommentRegister()");

        service.register(qnaComment, qnaNo);
    }

    @GetMapping("/{qnaNo}")
    public List<QnaComment> list (@PathVariable("qnaNo") Long qnaNo) {

        /*Boolean a = service.checkSecret(qnaNo);
        if (a== true) {
            log.info("CommentList()");
            return null;

        } else {
            return service.list(qnaNo);
        }*/
        return service.list(qnaNo);
    }

    @PutMapping("{qnaCommentNo}")
    public QnaComment CommentModify ( @PathVariable("qnaCommentNo") Long qnaCommentNo,
                                      @RequestBody QnaComment qnaComment,
                                      @RequestParam(value = "qnaNo") Long qnaNo) {
        log.info("CommentModify");

        qnaComment.setQnaCommentNo(qnaCommentNo);
        service.modify(qnaComment, qnaNo);

        return qnaComment;
    }

    @DeleteMapping("/{qnaCommentNo}")
    public void CommentDelete (@PathVariable("qnaCommentNo") Long qnaCommentNo, QnaComment qnComment) {
        log.info("CommentDelete()");

        service.delete(qnaCommentNo);
    }
}
