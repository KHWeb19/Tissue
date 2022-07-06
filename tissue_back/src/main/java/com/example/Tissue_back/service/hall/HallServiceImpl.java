package com.example.Tissue_back.service.hall;


import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.repository.hall.HallRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HallServiceImpl implements HallService{

    @Autowired
    private HallRepository hallRepository;


    @Override
    public void register(Hall hall) {
        hallRepository.save(hall);
    }

    @Override
    public List<Hall> list() {
        return hallRepository.findAll(Sort.by(Sort.Direction.DESC,"hallNo"));
    }

    @Override
    public Hall read(Long hallNo) {
        Optional<Hall> maybeHall = hallRepository.findById((hallNo));

        if (maybeHall.equals(Optional.empty())){
            log.info("No board");
            return null;
        }

        return maybeHall.get();
    }



    @Override
    public void remove(Long hallNo) {
        hallRepository.deleteById(hallNo);
    }


}
