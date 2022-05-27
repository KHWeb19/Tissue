package com.example.Tissue_back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
public class testController {

    @GetMapping("/register")
    public String register () {
        log.info("register()");

        return "/";
    }

}
