package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.LikesDto;
import com.example.Tissue_back.entity.performance.Likes;

public interface LikesService {
    public boolean register (LikesDto likesDto);
}
