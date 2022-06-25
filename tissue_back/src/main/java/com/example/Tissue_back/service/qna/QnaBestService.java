package com.example.Tissue_back.service.qna;

import com.example.Tissue_back.entity.qna.QnaBest;

import java.util.List;

public interface QnaBestService {
    public void register (QnaBest qnaBest);
    public List<QnaBest> list();
    public QnaBest read (Long qnaBestNo);
    public void modify (QnaBest qnaBestNo);
    public void remove (Long qnaBestNo);
}
