package com.example.Tissue_back.controller;

import com.example.Tissue_back.controller.request.LikesDto;
import com.example.Tissue_back.entity.performance.Likes;
import com.example.Tissue_back.service.LikesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/likes")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class LikesController {

    @Autowired
    private LikesService service;

    @PostMapping("/register")
    public boolean like (@Validated @RequestBody LikesDto likesDto){
        log.info("== I like ! ==" + likesDto);

        return service.register(likesDto);
    }
}
