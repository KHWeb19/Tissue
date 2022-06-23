package com.example.Tissue_back.service.hall;


import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.entity.hall.HallSeat;

public interface HallSeatService {
    public void register(Hall hall,Long hallNo);
    public void modify(HallSeat hallSeat, Long hallNo);
}
