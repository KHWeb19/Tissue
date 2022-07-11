package com.example.Tissue_back.controller.ranking;

import com.example.Tissue_back.controller.request.ranking.RankingDTO;
import com.example.Tissue_back.controller.request.ranking.RankingToday;
import com.example.Tissue_back.service.ranking.RankingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ranking")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/list")
    public List<RankingDTO> RankingToday () {
        log.info("Today Ranking");

        return rankingService.todayList();
    }

    @GetMapping("/list/byDate/{reviewRegDate}")
    public List<RankingDTO> RankingByDate (@PathVariable("reviewRegDate") String reviewRegDate) {
        log.info("Date Ranking!");
        log.info(String.valueOf(reviewRegDate));

        return rankingService.dateList(reviewRegDate);
    }


    @GetMapping("/main/list")
    public List<RankingDTO> RankingMain () {
        log.info("Today Ranking");

        return rankingService.mainList();
    }

    /*@GetMapping("/list")
    public List<Ticketing> QnaList() {
        log.info("Test");

        return rankingService.list();
    }*/


}
