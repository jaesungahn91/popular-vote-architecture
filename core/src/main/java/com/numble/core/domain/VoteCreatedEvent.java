package com.numble.core.domain;

import lombok.Getter;

import static com.numble.core.domain.EventType.VOTE_CREATED;

@Getter
public class VoteCreatedEvent extends Event {

    private Long puppyId;

    protected VoteCreatedEvent() {
        super(VOTE_CREATED);
    }

    public VoteCreatedEvent(Long puppyId) {
        super(VOTE_CREATED);
        this.puppyId = puppyId;
    }

}
