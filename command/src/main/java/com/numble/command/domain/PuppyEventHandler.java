package com.numble.command.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.numble.core.domain.PuppyCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Service
public class PuppyEventHandler {

    private final PuppyEventSourcing puppyEventSourcing;

    @TransactionalEventListener
    public void createEventHandle(PuppyCreatedEvent event) throws JsonProcessingException {
        puppyEventSourcing.send(event);
    }

}
