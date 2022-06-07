package com.example.Tissue_back.controller.hall;

import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.service.hall.HallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hall")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class HallController {

    @Autowired
    private HallService hallService;

    @PostMapping("/register")
    public void hallRegister(@Validated Hall hall){
        log.info("hallRegister()");

        hallService.register(hall);
    }
}
