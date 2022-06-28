package com.example.Tissue_back.service.event;

import com.example.Tissue_back.controller.request.event.EventDto;
import com.example.Tissue_back.entity.event.Event;
import com.example.Tissue_back.entity.performance.Performance;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    public void register(EventDto eventDto, Performance performance);
    public List<Event> list();
    public Event read(Integer eventNo);
    public Event modify(EventDto eventDto, Integer eventNo, Performance performance) throws Exception;
    public void remove(Integer eventNo);
}
