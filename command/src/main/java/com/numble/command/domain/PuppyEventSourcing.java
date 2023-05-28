package com.numble.command.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.numble.core.domain.Event;

public interface PuppyEventSourcing {

        void send(Event event) throws JsonProcessingException;

}
