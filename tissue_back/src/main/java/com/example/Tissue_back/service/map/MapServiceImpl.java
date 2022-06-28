package com.example.Tissue_back.service.map;


import com.example.Tissue_back.controller.request.map.MapDto;
import com.example.Tissue_back.entity.map.Map;
import com.example.Tissue_back.repository.map.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService{
    @Autowired
    MapRepository repository;

    @Override
    public Map add(MapDto mapDto) throws Exception {
        Map map = repository.save(mapDto.toEntity());

        return map;
    }

    @Override
    public Map read(Long performNo) throws Exception {
        Map map = repository.findByPerformNo(performNo).orElse(null);

        return map;
    }

    @Override
    public List<Map> list() throws Exception {
        List<Map> maps = repository.findAll();

        return maps;
    }

    @Override
    public void modify(Map map, MapDto mapDto) throws Exception {
        map.updateMap(mapDto);

        repository.save(map);
    }

    @Override
    public void remove(Map map) throws Exception {
        repository.delete(map);
    }
}
