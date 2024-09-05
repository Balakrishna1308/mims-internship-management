package com.mfa.mims.utils;

import com.mfa.mims.entity.Event;
import com.mfa.mims.service.EventService;

import java.util.List;
import java.util.function.Supplier;

public class UpcomingEventsSupplier implements Supplier<String> {

    private final EventService eventService;

    public UpcomingEventsSupplier(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public String get() {
        List<Event> upcomingEvents = eventService.getUpcomingEvents();

        //Generate a summary of upcoming events
        StringBuilder summary = new StringBuilder("Upcoming Events:\n");

        for ( Event event: upcomingEvents) {
          summary.append(String.format("Event: %s, Date: %s\n", event.getEventName(), event.getEventDate()));
        }

        return summary.toString();
    }
}
