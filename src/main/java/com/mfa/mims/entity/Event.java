package com.mfa.mims.entity;

import jakarta.persistence.*;

@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_code", nullable = false)
    private char eventCode;

    @Transient
    private short eventCodeAsShort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getEventCode() {
        return eventCode;
    }

    public void setEventCode(char eventCode) {
        this.eventCode = eventCode;
    }

    public short getEventCodeAsShort() {
        return (short) eventCode; //Narrowing from char to short
    }
}
