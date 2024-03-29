package com.example.Tissue_back.entity.ticketing;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TicketingSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketingSeatNo;

    @Column
    private String seatName ;

    @Column
    private Long performNo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ticketing_ticketingNo")
    private Ticketing ticketing;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ticketing2_ticketingNo")
    private Ticketing2 ticketing2;
}
