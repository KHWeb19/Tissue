package com.example.Tissue_back.controller;

import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/security")
@CrossOrigin(origins="http://localhost:8080", allowedHeaders = "*")
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @GetMapping("/check")
    public SecurityService.JwtCode check (@RequestParam(value = "token") String token) {
        log.info(token);
        return securityService.validateToken(token);
    }
}
