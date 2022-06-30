package com.example.Tissue_back.service.qna;

import com.example.Tissue_back.entity.qna.Qna;

import java.util.List;

public interface QnaService {
    public void register (Qna qna, Long memberNo);
    public List<Qna> list();
    public Qna read (Long qnaNo);
    public void modify (Qna qnaNo);
    public void remove (Long qnaNo);
    // 비밀글 확인
    public Boolean checkSecret (Long qnaNo);
    //비밀번호 일치 여부 확인
    public Boolean checkQnaPw (Integer qnaNo, String qnaPw);


}
