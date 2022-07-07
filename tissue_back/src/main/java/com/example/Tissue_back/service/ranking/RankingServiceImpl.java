package com.example.Tissue_back.service.ranking;

import com.example.Tissue_back.entity.Book;
import com.example.Tissue_back.repository.ranking.RankingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RankingServiceImpl implements RankingService {

    @Autowired
    RankingRepository repository;

    @Override
    public List<Book> list(Date bookDate) {

        //Sort sort = Sort.by(Sort.Order.desc("COUNT(b)"));

        return repository.RankedByDate(bookDate);


    }

}
