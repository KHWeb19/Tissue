package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.LikesDto;
import com.example.Tissue_back.entity.performance.Likes;

import java.util.List;

public interface LikesService {
    public List<Likes> iLike (LikesDto likesDto);
    public List<Likes> iDislike (String token, Long performNo);
    public List<Likes> howLikes (Long performNo);
    public List<Likes> myLikes (Long memberNo);
}
