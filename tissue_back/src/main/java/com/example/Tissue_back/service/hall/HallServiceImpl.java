package com.example.Tissue_back.service.hall;


import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.repository.hall.HallRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HallServiceImpl implements HallService{

    @Autowired
    private HallRepository hallRepository;

    @Override
    public void register(Hall hall) {

        hallRepository.save(hall);
    }
}
