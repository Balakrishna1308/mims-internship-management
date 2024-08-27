package com.mfa.mims.repository;

import com.mfa.mims.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
