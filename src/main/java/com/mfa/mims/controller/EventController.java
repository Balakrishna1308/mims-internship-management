package com.mfa.mims.controller;

import com.mfa.mims.entity.Event;
import com.mfa.mims.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping
    private ResponseEntity<Event> createEvent(@RequestBody Event event)
    {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @GetMapping
    private ResponseEntity<List<Event>> getAllEvents()
    {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}/code-short")
    private ResponseEntity<Short> getEventCodeAsShort(@PathVariable Long id)
    {
        short eventCodeAsShort = eventService.getEventCodeAsShort(id);
        return ResponseEntity.ok(eventCodeAsShort);
    }

    //Get upcoming events
    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents()
    {
        List<Event> upcomingEvents = eventService.getUpcomingEvents();
        return ResponseEntity.ok(upcomingEvents);
    }

    @GetMapping("/event-summary")
    public ResponseEntity<String> generateUpcomingEventsSummary()
    {
        String upcomingEventsSummary = eventService.generateUpcomingEventsSummary();
        return ResponseEntity.ok(upcomingEventsSummary);
    }
}
