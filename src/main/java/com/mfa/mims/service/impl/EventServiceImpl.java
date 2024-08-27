package com.mfa.mims.service.impl;

import com.mfa.mims.entity.Event;
import com.mfa.mims.repository.EventRepository;
import com.mfa.mims.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public short getEventCodeAsShort(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new RuntimeException("Event not found."));
        return event.getEventCodeAsShort();

    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
}
