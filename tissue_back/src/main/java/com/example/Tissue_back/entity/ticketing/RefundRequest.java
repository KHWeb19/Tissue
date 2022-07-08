package com.example.Tissue_back.entity.ticketing;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RefundRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundNo;

    @OneToOne
    @JoinColumn(name="ticketingNo")
    private Ticketing ticketing;
}
