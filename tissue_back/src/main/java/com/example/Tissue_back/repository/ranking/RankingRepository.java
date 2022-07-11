package com.example.Tissue_back.repository.ranking;

import com.example.Tissue_back.entity.ticketing.Ticketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ticketing, Long>{

   /* @Query(value =
            "SELECT performNo, COUNT(reviewRegDate) FROM Ticketing WHERE reviewRegDate >date_format(now(), '%Y-%m-%d') GROUP BY performNo ORDER BY COUNT(reviewRegDate) DESC limit 30"
            , nativeQuery = true)
    List<RankingDTO> RankedByToday ();*/

    /*@Query(name = "find_ranking_today_dto", nativeQuery = true)
    List<RankingToday> RankedTodayBy ();*/





    /*@Query(nativeQuery = true, value =
            "select performNo, COUNT(reviewRegDate) from Ticketing" +
            "where reviewRegDate = :reviewRegDate" +
            "group by performNo" +
            "order by COUNT(reviewRegDate) DESC limit 30")
    List<Ticketing> RankedByDate (@Param("reviewRegDate") String reviewRegDate);*/

}
