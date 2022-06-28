package com.example.Tissue_back.repository.event;

import com.example.Tissue_back.entity.event.Event;
import com.example.Tissue_back.entity.map.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

}
