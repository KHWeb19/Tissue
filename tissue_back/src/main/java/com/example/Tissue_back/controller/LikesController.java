package com.example.Tissue_back.controller;

import com.example.Tissue_back.controller.request.LikesDto;
import com.example.Tissue_back.entity.performance.Likes;
import com.example.Tissue_back.service.LikesService;
import com.example.Tissue_back.service.security.SecurityService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/likes")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class LikesController {

    @Autowired
    private LikesService service;
    @Autowired
    private SecurityService securityService;

    @PostMapping("/register")
    public List<Likes> iLike (@Validated @RequestBody LikesDto likesDto){
        log.info("== I like ! ==" + likesDto);

        return service.iLike(likesDto);
    }

    @DeleteMapping("/delete")
    public List<Likes> iDislike (@RequestParam(value="token") String token, @RequestParam(value="performNo") Long performNo) {

        log.info("== I Dislike ! ==" + token + performNo);

        return service.iDislike(token,performNo);
    }

    @GetMapping("/{performNo}")
    public List<Likes> getLike (@PathVariable("performNo") Long performNo) {
        log.info("get likes");

        return service.howLikes(performNo);
    }

    @GetMapping("/member")
    public Long no (@RequestParam(value="token") String token) {
        log.info("get memberNo");
        Claims no = securityService.getRole(token);
        return Long.valueOf(String.valueOf(no.get("memberNo")));
    }
}
