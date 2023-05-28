package com.numble.core.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Entity
public class Vote {

    @EmbeddedId
    private VoteKey voteKey;

    protected Vote() {
    }

    public Vote(VoteKey voteKey) {
        this.voteKey = voteKey;
    }

    public static Vote of(String ip, Puppy puppy) {
        return new Vote(new VoteKey(ip, puppy));
    }

    public Long getPuppyId() {
        return voteKey.getPuppy().getId();
    }

}
