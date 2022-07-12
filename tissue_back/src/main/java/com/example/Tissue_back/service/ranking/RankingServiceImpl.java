package com.example.Tissue_back.service.ranking;

import com.example.Tissue_back.controller.request.ranking.RankingDTO;
import com.example.Tissue_back.controller.request.ranking.RankingToday;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.sql.Date;
import java.util.List;

@Slf4j
@Service
public class RankingServiceImpl implements RankingService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<RankingDTO> todayList () {
       JpaResultMapper result = new JpaResultMapper();
        //오늘 날짜에 많이 예약된 공연 TOP 30
       Query query = em.createNativeQuery("SELECT " +
               "row_number() over(order by COUNT(*) desc) as ranking, t.perform_no, COUNT(*), " +
               "p.perform_name, p.perform_start, p.perform_end, p.perform_thumbnail, " +
               "h.hall_name, r.review_rating FROM Ticketing as t " +
               "LEFT OUTER JOIN Performance as p ON t.perform_no = p.perform_no " +
               "LEFT OUTER JOIN Hall as h ON h.hall_no = p.hall_name " +
               "LEFT OUTER JOIN Review as r ON r.perform_no = p.perform_no " +
               "WHERE t.ticketing_reg_date >DATE_FORMAT(now(), '%Y-%m-%d') " +
               "GROUP BY perform_no " +
               "ORDER BY COUNT(*) DESC " +
               "limit 30");

       return result.list(query, RankingDTO.class);
    }

    @Override
    public List<RankingDTO> dateList (String ticketingRegDate) {
        JpaResultMapper result = new JpaResultMapper();

        log.info("Service: ");
        log.info(ticketingRegDate);

        Query query = em.createNativeQuery("SELECT " +
                "row_number() over(order by COUNT(*) desc) as ranking, t.perform_no, COUNT(*), " +
                "p.perform_name, p.perform_start, p.perform_end, p.perform_thumbnail, " +
                "h.hall_name, r.review_rating FROM Ticketing as t " +
                "LEFT OUTER JOIN Performance as p ON t.perform_no = p.perform_no "+
                "LEFT OUTER JOIN Hall as h ON h.hall_no = p.hall_name " +
                "LEFT OUTER JOIN Review as r ON r.perform_no = p.perform_no " +
                "WHERE DATE_FORMAT(t.ticketing_reg_date, '%Y-%m-%d') = :ticketingRegDate " +
                "GROUP BY perform_no " +
                "ORDER BY COUNT(*) DESC " +
                "limit 30")
                .setParameter("ticketingRegDate", ticketingRegDate);


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
