package com.numble.core.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class Event {

    private final UUID uuid = UUID.randomUUID();
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final EventType eventType;

    public Event(EventType eventType) {
        this.eventType = eventType;
    }
}
