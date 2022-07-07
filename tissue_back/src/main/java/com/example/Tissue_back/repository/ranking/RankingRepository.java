package com.example.Tissue_back.repository.ranking;

import com.example.Tissue_back.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RankingRepository extends JpaRepository<Book, Long>{

    @Query(nativeQuery = true, value =
            "select performNo, COUNT(bookDate) from Book" +
            "where bookDate = :bookDate" +
            "group by performNo" +
            "order by COUNT(bookDate) DESC")
    List<Book> RankedByDate (@Param("bookDate") Date bookDate);

}
