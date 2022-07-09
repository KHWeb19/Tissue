package com.example.Tissue_back.service.ranking;

import com.example.Tissue_back.controller.request.ranking.RankingDTO;
import com.example.Tissue_back.controller.request.ranking.RankingToday;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Slf4j
@Service
public class RankingServiceImpl implements RankingService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<RankingToday> todayList () {
       JpaResultMapper result = new JpaResultMapper();
        //오늘 날짜에 가장 많이 예약된 공연 TOP 30
       Query query = em.createNativeQuery("SELECT row_number() over(order by COUNT(*) desc) as ranking, t.perform_no, COUNT(*), p.perform_name, p.perform_start, p.perform_end, p.perform_thumbnail, h.hall_name FROM Ticketing as t left outer join Performance as p on t.perform_no = p.perform_no left outer join Hall as h on h.hall_no = p.hall_name WHERE t.review_reg_date >date_format(now(), '%Y-%m-%d') GROUP BY perform_no ORDER BY COUNT(*) DESC limit 30");

       return result.list(query, RankingToday.class);
       /*List<RankingToday> list = result.list(query, RankingToday.class);
       return list;*/
    }

    @Override
    public List<RankingDTO> dateList (String reviewRegDate) {
        JpaResultMapper result = new JpaResultMapper();

        Query query = em.createNativeQuery("SELECT row_number() over(order by COUNT(*) desc) as ranking, t.perform_no, COUNT(*), p.perform_name, p.perform_start, p.perform_end, p.perform_thumbnail, h.hall_name FROM Ticketing as t left outer join Performance as p on t.perform_no = p.perform_no left outer join Hall as h on h.hall_no = p.hall_name WHERE t.review_reg_date = :review_reg_date GROUP BY perform_no ORDER BY COUNT(*) DESC limit 30")
                .setParameter("review_reg_date", reviewRegDate);

        return result.list(query, RankingDTO.class);
    }

    /*@Override
    public List<Ticketing> dateList(String reviewRegDate) {

        //Sort sort = Sort.by(Sort.Order.desc("COUNT(b)"));

        return repository.RankedByDate(reviewRegDate);
    }

    @Override
    public List<Ticketing> list() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "ticketingNo"));
    }*/

}
