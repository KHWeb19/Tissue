package com.example.Tissue_back.controller.QnA;

import com.example.Tissue_back.entity.qna.Qna;
import com.example.Tissue_back.service.qna.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/qna")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QnaController {

    @Autowired
    private QnaService service;

    @PostMapping("/register")
    public void QnaRegister (@Validated @RequestBody Qna qna){
        log.info("Qna Register");

        service.register(qna);
    }

    @GetMapping("/list")
    public List<Qna> QnaList() {
        log.info("Qna List");

        return service.list();
    }

    @GetMapping("/{qnaNo}")
    public Qna QnaRead (@PathVariable("qnaNo") Long qnaNo) {

            log.info("Qna Read");
            return service.read(qnaNo);

    }

    @GetMapping("/CheckQnaPw")
    public Boolean CheckQnaPw (//@PathVariable("qnaNo") Long qnaNo,
                               @RequestParam(value = "qnaNo") Integer qnaNo,
                               @RequestParam(value = "qnaPw") String qnaPw) {

            return service.checkQnaPw(qnaNo, qnaPw);

    }

    @PutMapping("/{qnaNo}")
    public Qna QnaModify (@PathVariable("qnaNo") Long qnaNo,
                              @RequestBody Qna qna) {
        log.info("Qna Modify ");

        qna.setQnaNo(qnaNo);
        service.modify(qna);

        return qna;
    }

    @DeleteMapping("/delete/{qnaNo}")
    public void QnaDelete (@PathVariable("qnaNo") Long qnaNo, Qna qna) throws Exception {
        log.info("QnaDelete");

        service.remove(qnaNo);
    }
}
