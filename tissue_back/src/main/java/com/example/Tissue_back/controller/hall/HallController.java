package com.example.Tissue_back.controller.hall;


import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.service.hall.HallSeatService;
import com.example.Tissue_back.service.hall.HallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hall")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private HallSeatService hallSeatService;

    @PostMapping("/register")
    public void hallRegister(@Validated @RequestBody Hall hall){
        log.info("hallRegister()");

        hallService.register(hall);
        hallSeatService.register(hall,hall.getHallNo());
    }

    @GetMapping("/list")
    public List<Hall> hallList(){
        log.info("hallList()");

        return hallService.list();
    }

    @GetMapping("/{hallNo}")
    public Hall hallRead(@PathVariable("hallNo")Long hallNo){
        log.info("hallRead()" +hallNo);

        return hallService.read(hallNo);
    }

    

}
