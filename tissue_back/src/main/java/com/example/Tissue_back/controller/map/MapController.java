package com.example.Tissue_back.controller.map;


import com.example.Tissue_back.controller.request.map.MapDto;
import com.example.Tissue_back.entity.map.Map;
import com.example.Tissue_back.service.map.MapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/map")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MapController {
    @Autowired
    MapService mapService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody MapDto mapDto) throws Exception {
        log.info("add map()");

        Map map = mapService.add(mapDto);

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @GetMapping("/read/{performNo}")
    public ResponseEntity read(@PathVariable("performNo") Long performNo) throws Exception {
        log.info("read map()");

        Map map = mapService.read(performNo);

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Map>> list() throws Exception {
        log.info("list map() ");

        List<Map> maps = mapService.list();

        return new ResponseEntity<List<Map>> (maps, HttpStatus.OK);
    }

    @PatchMapping("/modify/{performNo}")
    public ResponseEntity<Void> modify (@PathVariable("performNo") Long performNo,
                                        @RequestBody MapDto mapDto) throws Exception {
        log.info("modify map() ");

        Map map = mapService.read(performNo);

        mapService.modify(map, mapDto);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/remove/{performNo}")
    public ResponseEntity<Void> remove(@PathVariable("performNo") Long performNo) throws Exception {
        log.info("remove map() ");

        Map map = mapService.read(performNo);

        mapService.remove(map);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
