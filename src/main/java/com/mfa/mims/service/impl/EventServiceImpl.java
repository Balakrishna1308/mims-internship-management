package com.mfa.mims.service.impl;

import com.mfa.mims.entity.Event;
import com.mfa.mims.repository.EventRepository;
import com.mfa.mims.service.EventService;
import com.mfa.mims.utils.UpcomingEventsSupplier;
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

    @Override
    public List<Event> getUpcomingEvents() {

        return eventRepository.findUpComingEvents();

    }

    @Override
    public String generateUpcomingEventsSummary() {

        //Use the Supplier to generate a summary of upcoming events
        UpcomingEventsSupplier supplier = new UpcomingEventsSupplier(this);
        return supplier.get();
    }
}
