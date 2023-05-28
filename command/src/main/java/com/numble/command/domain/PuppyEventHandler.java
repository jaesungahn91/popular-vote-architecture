package com.numble.command.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.numble.core.domain.PuppyCreatedEvent;
import com.numble.core.domain.VoteCreatedEvent;
import com.numble.core.domain.VoteDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Service
public class PuppyEventHandler {

    private final PuppyEventSourcing puppyEventSourcing;

    @TransactionalEventListener
    public void createPuppyEventHandle(PuppyCreatedEvent event) throws JsonProcessingException {
        puppyEventSourcing.send(event);
    }

    @TransactionalEventListener
    public void createVoteEventHandle(VoteCreatedEvent event) throws JsonProcessingException {
        puppyEventSourcing.send(event);
    }

    @TransactionalEventListener
    public void deleteVoteEventHandle(VoteDeletedEvent event) throws JsonProcessingException {
        puppyEventSourcing.send(event);
    }


}
