package com.numble.core.domain;

import static com.numble.core.domain.EventType.VOTE_DELETED;

public class VoteDeletedEvent extends Event {

    private Long puppyId;

    protected VoteDeletedEvent() {
        super(VOTE_DELETED);
    }

    public VoteDeletedEvent(Long puppyId) {
        super(VOTE_DELETED);
        this.puppyId = puppyId;
    }

}
