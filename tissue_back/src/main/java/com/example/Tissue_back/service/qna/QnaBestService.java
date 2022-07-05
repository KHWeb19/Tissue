package com.example.Tissue_back.service.qna;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.qna.QnaBest;
import io.jsonwebtoken.Claims;

import java.util.List;

public interface QnaBestService {
    public void register (QnaBest qnaBest);
    public List<QnaBest> list();
    public Claims getRole (String token);
    public QnaBest read (Long qnaBestNo);
    public void modify (QnaBest qnaBestNo);
    public void remove (Long qnaBestNo);
}
