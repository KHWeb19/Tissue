package com.example.Tissue_back.controller.ranking;

import com.example.Tissue_back.entity.Book;
import com.example.Tissue_back.entity.qna.QnaBest;
import com.example.Tissue_back.service.ranking.RankingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ranking")
@CrossOrigin(origins = "https://localhost:8080", allowedHeaders = "*")
public class RankingController {

    @Autowired
    RankingService rankingService;


    @GetMapping("/list")
    public List<Book> Ranking(@RequestParam(value = "bookDate") Date bookDate) {
        log.info("Ranking!");

        return rankingService.list(bookDate);
    }

}
