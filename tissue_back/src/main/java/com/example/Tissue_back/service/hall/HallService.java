package com.example.Tissue_back.service.hall;
import java.util.List;

import com.example.Tissue_back.entity.hall.Hall;

public interface HallService {
    public void register(Hall hall);
    public List<Hall> list();
    public Hall read(Long hallNo);

    public void remove(Long hallNo);
}
