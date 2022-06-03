package com.example.Tissue_back.controller;

import com.example.Tissue_back.service.PhoneCheckService;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/phone")
@CrossOrigin(origins="http://localhost:8080", allowedHeaders = "*")
public class PhoneCheckController {

    @Autowired
    PhoneCheckService service;

    @GetMapping("/check/{memberPhone}")
    public @ResponseBody String sendSMS (@PathVariable("memberPhone") String memberPhone) throws CoolsmsException {
        log.info(memberPhone);
        return service.PhoneNumberCheck(memberPhone);
    }

}
