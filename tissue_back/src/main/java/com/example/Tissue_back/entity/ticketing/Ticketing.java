package com.example.Tissue_back.entity.ticketing;

import com.example.Tissue_back.entity.hall.HallSeat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Data
@Entity
public class Ticketing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketingNo;

    @Column
    private String memberId;

    @Column
    private Long performNo;

    @Column
    private String []seatNameArr;

    @Column
    private Integer usedMileage;

    @Column
    private Long usedCouponNo;

    @CreatedDate
    private String ticketingRegDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Column
    private Integer finalPrice;


    @OneToMany(mappedBy = "ticketing", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<TicketingSeat> seats;

    @Column
    public String status = "예매완료";

}
