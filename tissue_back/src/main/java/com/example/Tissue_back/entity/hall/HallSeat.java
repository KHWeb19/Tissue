package com.example.Tissue_back.entity.hall;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class HallSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatNo;

    @Column
    private String seatName;

    @Column
    private String seatGrade;

    @ManyToOne
    @JoinColumn(name = "hall_hallName")
    private Hall hall;
}
