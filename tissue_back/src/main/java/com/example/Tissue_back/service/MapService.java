package com.example.Tissue_back.service;

import com.example.Tissue_back.controller.request.performance.MapDto;
import com.example.Tissue_back.entity.Map;

import java.util.List;

public interface MapService {
    public Map add(MapDto mapDto) throws Exception;
    public Map read(Long performNo) throws Exception;
    public List<Map> list() throws Exception;
    public void modify(Map map, MapDto mapDto) throws Exception;
    public void remove(Map map) throws Exception;
}
