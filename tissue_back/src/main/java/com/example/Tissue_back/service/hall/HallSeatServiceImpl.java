package com.example.Tissue_back.service.hall;

import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.entity.hall.HallSeat;
import com.example.Tissue_back.repository.hall.HallRepository;
import com.example.Tissue_back.repository.hall.HallSeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class HallSeatServiceImpl implements HallSeatService{

    @Autowired
    private HallSeatRepository hallSeatRepository;

    @Autowired
    private HallRepository hallRepository;

    @Override
    public void register(Hall hall, Long hallNo) {
        HallSeat hallSeat = new HallSeat();
        Optional<Hall> findHall = hallRepository.findById(hallNo);

        int nameCnt = 0;

        for (int i =0; i < hall.getRowCnt(); i++){
            for (int j = 0; j < hall.getColCnt(); j++){

                nameCnt = nameCnt + 1;
                String name = Integer.toString(nameCnt);

                hallSeat.setSeatName(name);
                hallSeat.setSeatGrade("R");
                hallSeat.setHall(findHall.get());
                hallSeatRepository.save(hallSeat);
                hallSeat.setSeatNo(null);
            }
        }
    }
}
