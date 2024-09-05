package com.mfa.mims.service;

import com.mfa.mims.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    short getEventCodeAsShort(Long eventId);
    Event createEvent(Event event);

    List<Event> getUpcomingEvents();
    String generateUpcomingEventsSummary();
}
