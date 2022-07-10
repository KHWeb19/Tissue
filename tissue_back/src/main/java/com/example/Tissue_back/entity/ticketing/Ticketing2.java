package com.example.Tissue_back.entity.ticketing;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Ticketing2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketingNo;

    @Column
    private String serialNumber;

    @Column
    private String phone;

    @Column
    private Long performNo;

    @Column
    private String [] seatNameArr;

    @CreatedDate
    private String ticketingRegDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));

    @Column
    private Integer finalPrice;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "ticketing2", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TicketingSeat> seats;

    @Column
    public String status = "예매완료";

}
