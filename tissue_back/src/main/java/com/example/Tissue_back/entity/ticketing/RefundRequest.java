package com.example.Tissue_back.entity.ticketing;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Entity
@Data
public class RefundRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundNo;

    @CreatedDate
    private String refundRegDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));;

    @OneToOne
    @JoinColumn(name="ticketingNo")
    private Ticketing ticketing;

    @OneToOne
    @JoinColumn(name="ticketing2No")
    private Ticketing2 ticketing2;
}
