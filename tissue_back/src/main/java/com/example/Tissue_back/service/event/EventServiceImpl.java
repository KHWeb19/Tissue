package com.example.Tissue_back.service.event;

import com.example.Tissue_back.controller.request.event.EventDto;
import com.example.Tissue_back.entity.event.Event;
import com.example.Tissue_back.entity.performance.Performance;
import com.example.Tissue_back.repository.event.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void register(EventDto eventDto, Performance performance) {
        Event event = new Event();

        event.setEventTitle(eventDto.getEventTitle());
        event.setEventCategory(eventDto.getEventCategory());
        event.setEventStart(eventDto.getEventStart());
        event.setEventEnd(eventDto.getEventEnd());
        event.setPerformance(performance);

        eventRepository.save(event);
    }

    @Override
    public List<Event> list() {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "eventNo"));
    }

    @Override
    public Event read(Integer eventNo) {
        Optional<Event> maybeEvent = eventRepository.findById(Long.valueOf(eventNo));

        if(maybeEvent.equals(Optional.empty())) {
            log.info("Cannot read event");
            return null;
        }
        return maybeEvent.get();
    }

    @Override
    public Event modify(Event event, List<MultipartFile> fileList) {

        return event;
    }

    @Override
    public void remove(Integer eventNo) {

    }
}
