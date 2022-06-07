package com.example.Tissue_back.entity.hall;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallNo;

    @Column
    private String hallName;

    @Column
    private int seatRow;

    @Column
    private int seatColumn;

    @Column
    private String seatGrade;
}
