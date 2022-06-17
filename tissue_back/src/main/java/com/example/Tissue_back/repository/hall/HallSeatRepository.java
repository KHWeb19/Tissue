package com.example.Tissue_back.repository.hall;

import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.entity.hall.HallSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HallSeatRepository extends JpaRepository<HallSeat, Long> {

    List<HallSeat>findHallSeatByHall(Hall hall);
}
