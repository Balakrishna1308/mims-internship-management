package com.mfa.mims.repository;

import com.mfa.mims.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.eventDate >= CURRENT_DATE")
    List<Event> findUpComingEvents();
}
