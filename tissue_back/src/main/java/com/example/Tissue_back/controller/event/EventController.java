package com.example.Tissue_back.controller.event;

import com.example.Tissue_back.controller.request.event.EventDto;
import com.example.Tissue_back.entity.event.Event;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.repository.performance.PerformanceRepository;
import com.example.Tissue_back.service.event.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private PerformanceRepository performanceRepository;

    @PostMapping("/register")
    public ResponseEntity eventRegister (@RequestBody EventDto eventDto) {
        log.info("register event()"+ eventDto);
        Optional<Performance> optionalPerformance = performanceRepository.findByPerformNo(eventDto.getPerformNo());

        if(optionalPerformance.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        eventService.register(eventDto, optionalPerformance.get());

        log.info("eventRegister success()");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Event> eventList() {
        log.info("eventList()");

        return eventService.list();
    }

    @GetMapping("/{eventNo}")
    public Event eventRead (
            @PathVariable("eventNo") Integer eventNo) {
        log.info("eventRead");

        return eventService.read(eventNo);
    }

    @PutMapping("/{eventNo}")
    public Event eventModify (
            @PathVariable("eventNo") Integer eventNo,
            @RequestPart("event") Event event,
            @RequestParam(value = "fileList", required = false ) List<MultipartFile> fileList) {
        log.info ("eventModify(): " + event);

        event.setEventNo(Long.valueOf(eventNo));

        Event event1 = eventService.modify(event, fileList);

        return event1;
    }

    @DeleteMapping("/{eventNo}")
    public void eventRemove (
            @PathVariable("eventNo") Integer eventNo) {
        log.info("eventRemove()");

        eventService.remove(eventNo);
    }
}
