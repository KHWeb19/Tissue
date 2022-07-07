package com.example.Tissue_back.service.ranking;

import com.example.Tissue_back.entity.Book;

import java.util.Date;
import java.util.List;

public interface RankingService {
    public List<Book> list(Date bookDate);
}
