package com.numble.core.domain;

import lombok.Getter;

import static com.numble.core.domain.EventType.PUPPY_CREATED;

@Getter
public class PuppyCreatedEvent extends Event {

    private Long puppyId;

    protected PuppyCreatedEvent() {
        super(PUPPY_CREATED);
    }

    public PuppyCreatedEvent(Long puppyId) {
        super(PUPPY_CREATED);
        this.puppyId = puppyId;
    }

}
