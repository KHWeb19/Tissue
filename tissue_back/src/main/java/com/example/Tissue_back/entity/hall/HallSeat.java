package com.example.Tissue_back.entity.hall;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class HallSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatNo;

    @Column
    private String seatName;

    @Column
    private String seatGrade;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hall_hallName")
    private Hall hall;

    public HallSeat(Long seatNo,String seatName, String seatGrade){
        this.seatNo = seatNo;
        this.seatName = seatName;
        this.seatGrade = seatGrade;


    }
}
