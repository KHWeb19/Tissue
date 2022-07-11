package com.example.Tissue_back.service.ranking;

import com.example.Tissue_back.controller.request.ranking.RankingDTO;
import com.example.Tissue_back.controller.request.ranking.RankingToday;

import java.sql.Date;
import java.util.List;

public interface RankingService {
    public List<RankingDTO> todayList ();
    public List<RankingDTO> dateList(String reviewRegDate);

}
