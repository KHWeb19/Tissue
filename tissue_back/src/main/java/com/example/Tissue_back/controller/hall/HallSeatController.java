package com.example.Tissue_back.controller.hall;


import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.entity.hall.HallSeat;
import com.example.Tissue_back.service.hall.HallSeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hallSeat")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class HallSeatController {

    @Autowired
    private HallSeatService hallSeatService;

    @PutMapping("/{hallNo}")
    public void hallSeatModify(@PathVariable("hallNo") Long hallNo, @Validated @RequestBody HallSeat hallSeat){
        log.info("hallModify()" + hallNo);

        hallSeatService.modify(hallSeat, hallNo);

    }
}
